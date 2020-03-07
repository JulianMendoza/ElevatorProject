package sysc3033.group9.elevatorproject.system;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
		String s2 = "Give me some info from server";
		IP = InetAddress.getLocalHost();
		data = new byte[1024];
		clientData = new DatagramPacket(data, data.length, IP, 3333);
		clientReply = new DatagramPacket(s.getBytes(), s.getBytes().length, IP, 3333);
		request = new byte[1024];
		serverRequest = new DatagramPacket(s2.getBytes(), s2.getBytes().length, IP, 5555);
		serverReply = new DatagramPacket(data, data.length, IP, 5555);
	}

	@Override
	public void run() {
		try {
			client.receive(clientData);
			client.send(clientReply);
			server.receive(serverRequest);
			server.send(serverReply);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
