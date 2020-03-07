package sysc3033.group9.elevatorproject.system;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import sysc3033.group9.elevatorproject.event.EventFile;

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
		serverReply = new DatagramPacket(s2.getBytes(), s2.getBytes().length, IP, 5555);
	}

	private void deserializeObject(byte[] object) {
		try {
			bis = new ByteArrayInputStream(object);
			in = new ObjectInputStream(bis);
			EventFile file = (EventFile) (in.readObject());
			file.test();
		} catch (IOException ex) {

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
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
			server.send(serverReply);
			System.out.println("THE SCHEDULUER HAS SCHEDULUED THE EVENT");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
