package sysc3033.group9.elevatorproject.system;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import sysc3033.group9.elevatorproject.constants.FilePath;
import sysc3033.group9.elevatorproject.event.EventFile;
import sysc3033.group9.elevatorproject.event.FloorEvent;
import sysc3033.group9.elevatorproject.util.Parser;

/**
 * ClientToServer will become a thread that handles the data packet coming from
 * the client which will then go to the server. This thread takes the packet
 * from the client and sends a reply. It then takes a packet(request) from the
 * server and then sends the data from the client as a reply.
 * 
 * @author Julian Mendoza
 *
 */
public class ClientToServer implements Runnable {
	private DatagramSocket client, server;
	private byte data[], request[];
	private DatagramPacket clientData, clientReply, serverRequest, serverReply;
	private InetAddress IP;
	private ObjectInput in;
	private ByteArrayInputStream bis;
	private EventFile file;
	private FloorEvent[] events;
	private ObjectOutputStream out;
	private ByteArrayOutputStream bos;

	/**
	 * Constructor of the thread
	 * 
	 * @param client Datasocket of the client
	 * @param server Datasocket of the server
	 * @throws UnknownHostException if the IP cannot be received
	 */
	public ClientToServer(DatagramSocket client, DatagramSocket server) throws UnknownHostException {
		this.client = client;
		this.server = server;
		String s = "Hello from the scheduler";
		String s2 = "Give me some info from scheduler";
		IP = InetAddress.getLocalHost();
		data = new byte[1024];
		clientData = new DatagramPacket(data, data.length, IP, 3333);
		clientReply = new DatagramPacket(s.getBytes(), s.getBytes().length, IP, 3333);
		request = new byte[1024];
		serverRequest = new DatagramPacket(data, data.length, IP, 5555);
		bos = new ByteArrayOutputStream();
	}

	private void deserializeObject(byte[] object) {
		try {
			bis = new ByteArrayInputStream(object);
			in = new ObjectInputStream(bis);
			file = (EventFile) (in.readObject());
		} catch (IOException ex) {

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void createEvents() {
		events = Parser.readTextFile(FilePath.EVENT_FILE, file.getFile());
		byte[] data = createEventObject(events[0]);
		serverReply = new DatagramPacket(data, data.length, IP, 5555);
		byte[] data2 = createEventObject(events[1]);
		clientData = new DatagramPacket(data2, data2.length, IP, 5556);
		try {
			server.send(clientData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] data3 = createEventObject(events[2]);
		clientData = new DatagramPacket(data3, data3.length, IP, 5557);
		try {
			server.send(clientData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private byte[] createEventObject(Object o) {
		byte[] byteStream = null;

		try {
			out = new ObjectOutputStream(bos);
			out.writeObject(o);
			out.flush();
			byteStream = bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteStream;
	}

	@Override
	public void run() {
		try {
			System.out.println("THE SCHEDULER IS WAITING FOR A PACKET");
			client.receive(clientData);
			System.out.println("THE SCHEDULER HAS RECEIVED A REQUEST:");
			deserializeObject(clientData.getData());
			client.send(clientReply);
			System.out.println("THE SCHEDULER HAS SENT A RESPONSE");
			System.out.println("THE SCHEDULER IS WAITING FOR AN ELEVATOR");
			serverRequest = new DatagramPacket(new byte[1024], 1024);
			server.receive(serverRequest);
			System.out.println("THE SCHEDULER HAS BEEN NOTIFIED BY AN ELEVATOR");
			System.out.println(new String(serverRequest.getData()));
			createEvents();
			server.send(serverReply);
			System.out.println("THE SCHEDULER HAS SCHEDULED THE EVENT");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
