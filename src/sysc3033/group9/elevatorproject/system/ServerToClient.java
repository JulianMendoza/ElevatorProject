package sysc3033.group9.elevatorproject.system;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
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
	private DatagramSocket client, server;
	private DatagramPacket serverAck, serverReply, clientRequest, clientReply;
	private InetAddress IP;

	/**
	 * Constructor of the thread
	 * 
	 * @param client DatagramSocket of the client
	 * @param server DatagramSocket of the server
	 * @throws UnknownHostException if the IP cannot be established
	 */
	public ServerToClient(DatagramSocket client, DatagramSocket server) throws UnknownHostException {
		this.client = client;
		this.server = server;
		String s = "Good bye from Elevator";
		IP = InetAddress.getLocalHost();
		ack = new byte[1024];
		serverAck = new DatagramPacket(ack, ack.length, IP, 4445);
		reply = new byte[1024];
		serverReply = new DatagramPacket(s.getBytes(), s.getBytes().length, IP, 4445);
		request = new byte[1024];
		clientRequest = new DatagramPacket(request, request.length, IP, 3333);
		clientReply = new DatagramPacket(ack, ack.length, IP, 3333);
	}

	@Override
	public void run() {
		try {
			server.receive(serverAck);
			server.send(serverReply);
			client.receive(clientRequest);
			client.send(clientReply);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}