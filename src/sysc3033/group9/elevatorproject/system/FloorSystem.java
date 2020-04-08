package sysc3033.group9.elevatorproject.system;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

import sysc3033.group9.elevatorproject.constants.Port;
import sysc3033.group9.elevatorproject.event.EventFile;
import sysc3033.group9.elevatorproject.event.FloorEvent;

/**
 * FloorSubSystem class contains the knowledge of all floors. Button presses and
 * display lamps are updated here
 * 
 * @author Julian Mendoza, Giuseppe Papalia
 *
 */
public class FloorSystem {

	private EventFile eventFile;
	private MulticastSocket socket;
	private DatagramPacket sendPacket, receivePacket;
	private ObjectOutputStream out;
	private ByteArrayOutputStream bos;
	private byte[] eventFileObject;

	public FloorSystem(EventFile eventFile) {
		this.eventFile = eventFile;
		try {
			socket = new MulticastSocket(Port.FLOOR_SYSTEM);
			socket.joinGroup(InetAddress.getByName("225.6.7.8"));
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.bos = new ByteArrayOutputStream();
		createEventObject();
	}

	private void process() throws IOException {
		String s2 = "Give me data";
		System.out.println("SENDING A REQUEST TO THE SCHEDULER");
		sendPacket = new DatagramPacket(eventFileObject, eventFileObject.length, InetAddress.getByName("225.6.7.8"),
				4444);
		socket.send(sendPacket);
		receivePacket = new DatagramPacket(new byte[1024], 1024);
		socket.receive(receivePacket);
		System.out.println("RECEIVED A RESPONSE FROM THE SCHEDULER");
		System.out.println(new String(receivePacket.getData()));
		sendPacket = new DatagramPacket(s2.getBytes(), s2.getBytes().length, InetAddress.getByName("225.6.7.8"), 4444);
		System.out.println("REQUESTING DATA FROM THE SCHEDULER"); // this will be display lamps and should continuously
																	// loop
		socket.send(sendPacket);
		socket.receive(receivePacket);
		System.out.println("Got some data");
		System.out.println(new String(receivePacket.getData()));
	}

	private void createEventObject() {
		try {
			out = new ObjectOutputStream(bos);
			out.writeObject(this.eventFile);
			out.flush();
			eventFileObject = bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		System.setProperty("java.net.preferIPv4Stack", "true");
		FloorEvent e = new FloorEvent();
		for (int i = 0; i < 2; i++) {
			e.createNewEvent();
		}
		FloorSystem floor = new FloorSystem(new EventFile());
		floor.process();
	}

}