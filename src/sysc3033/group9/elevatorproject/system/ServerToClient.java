package sysc3033.group9.elevatorproject.system;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * ServerToClient will be a thread containing the packet of the server to the
 * client. This thread stores the acknowledgement packet from the server and
 * sends a reply. It then accepts a request from the server and sends a reply in
 * the form of the acknowledgement
 * 
 * @author Julian Mendoza
 *
 */
public class ServerToClient implements Runnable {
	private byte ack[], reply[], request[];
	private MulticastSocket client, server;
	private DatagramPacket serverAck, serverReply, clientRequest, clientReply;
	private InetAddress IP;

	/**
	 * Constructor of the thread
	 * 
	 * @param client DatagramSocket of the client
	 * @param server DatagramSocket of the server
	 * @throws UnknownHostException if the IP cannot be established
	 */
	public ServerToClient(MulticastSocket client, MulticastSocket server) throws UnknownHostException {
		this.client = client;
		this.server = server;
		String s = "Good bye from Scheduler";
		IP = InetAddress.getByName("225.6.7.8");
		ack = new byte[1024];
		serverAck = new DatagramPacket(ack, ack.length, IP, 5555);
		reply = new byte[1024];
		serverReply = new DatagramPacket(s.getBytes(), s.getBytes().length, IP, 5555);
		request = new byte[1024];
		clientRequest = new DatagramPacket(request, request.length, IP, 3333);
		clientReply = new DatagramPacket(ack, ack.length, IP, 3333);
	}

	@Override
	public void run() {
		try {
			System.out.println("THE SCHEDULER IS WAITING FOR DATA");
			server.receive(serverAck);
			System.out.println("THE SCHEDULER HAS RECEIVED SOME DATA");
			System.out.println(new String(serverAck.getData()));
			server.send(serverReply);
			System.out.println("THE SCHEDULER HAS NOTIFIED THE SERVER ABOUT THE DATA");
			System.out.println("THE SCHEDULER IS WAITING FOR THE FLOOR");
			client.receive(clientRequest);
			System.out.println("THE SCHEDULER HAS RECEIVED THE REQUEST FROM THE FLOOR");
			System.out.println(new String(clientRequest.getData()));
			client.send(clientReply);
			System.out.println("THE SCHEDULER HAS SEND THE DATA TO THE FLOOR");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}