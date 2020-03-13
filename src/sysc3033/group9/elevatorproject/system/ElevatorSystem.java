package sysc3033.group9.elevatorproject.system;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import sysc3033.group9.elevatorproject.constants.Port;
import sysc3033.group9.elevatorproject.elevator.Elevator;
import sysc3033.group9.elevatorproject.event.ElevatorEvent;
import sysc3033.group9.elevatorproject.event.FloorEvent;
import sysc3033.group9.elevatorproject.floor.FloorSpan;

/**
 * ElevatorSystem thread Handles events that the elevators must process
 * 
 * @author Julian Mendoza, Giuseppe Papalia
 *
 */
public class ElevatorSystem {

	// Map<Integer, Elevator> elevators;
	FloorEventQueue eventQueue;
	private DatagramSocket socket;
	private DatagramPacket requestPacket, dataPacket, ackPacket;
	private InetAddress IP;
	private Elevator elevator;
	private ObjectInput in;
	private ByteArrayInputStream bis;
	private FloorEvent event;
	private ByteArrayOutputStream bos;
	private int port;

	public ElevatorSystem(FloorSpan floorSpan) {
		port = Port.ELEVATOR_SYSTEM;
		while (socket == null) {
			try {
				socket = new DatagramSocket(port);
			} catch (SocketException e) {
				port++;
			}
		}
		try {
			IP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		elevator = new Elevator(floorSpan);
		System.out.println("PORT NUMBER: " + port);
	}

	/**
	 * public ElevatorSystem(FloorSpan floorSpan, int numElevators) { elevators =
	 * new HashMap<Integer, Elevator>(); for (int i = 0; i < numElevators; i++) {
	 * elevators.put(i, new Elevator(floorSpan)); } eventQueue = new
	 * FloorEventQueue(elevators); }
	 * 
	 * public synchronized void scheduleEvent(FloorEvent e, int elevatorCarID) {
	 * eventQueue.add(e, elevators.get(elevatorCarID)); }
	 */

	private void handleFloorEvent(FloorEvent e, Elevator elevator) {
		ElevatorEvent e2 = createElevatorEvent(e, elevator);
		dispatchElevatorEvent(e2);
	}

	private ElevatorEvent createElevatorEvent(FloorEvent e, Elevator elevator) {
		return new ElevatorEvent(elevator, e);
	}

	private void dispatchElevatorEvent(ElevatorEvent e) {
		Thread elevatorEvent = new Thread(e);
		elevatorEvent.start();
	}

	private void process() {
		if (port == Port.ELEVATOR_SYSTEM) {
			String s = "Request from the elevator";
			String s2 = "Processed data is here";
			requestPacket = new DatagramPacket(s.getBytes(), s.getBytes().length, IP, 4445);
			try {
				System.out.println("SENDING A REQUEST TO THE SCHEDULER");
				socket.send(requestPacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dataPacket = new DatagramPacket(new byte[1024], 1024);
			try {
				socket.receive(dataPacket);
				System.out.println("RECEIVED APPROVAL FROM THE SCHEDULER");
				deserializeObject(dataPacket.getData());
				handleFloorEvent(this.event, this.elevator);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// process data
			ackPacket = new DatagramPacket(s2.getBytes(), s2.getBytes().length, IP, 4445);
			try {
				System.out.println("THE ELEVATOR HAS BEEN SCHEDULED, SENDING OUT DATA");
				socket.send(ackPacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dataPacket = new DatagramPacket(new byte[1024], 1024);
			try {

				socket.receive(dataPacket);
				System.out.println("SUCCESSFUL CONNECTION");
				System.out.println(new String(dataPacket.getData()));
				System.out.println("closing connection");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			dataPacket = new DatagramPacket(new byte[1024], 1024);
			try {
				socket.receive(dataPacket);
				System.out.println("RECEIVED APPROVAL FROM THE SCHEDULER");
				deserializeObject(dataPacket.getData());
				handleFloorEvent(this.event, this.elevator);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void deserializeObject(byte[] object) {
		try {
			bis = new ByteArrayInputStream(object);
			in = new ObjectInputStream(bis);
			event = (FloorEvent) (in.readObject());
			System.out.println(event);
		} catch (IOException ex) {

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ElevatorSystem system = new ElevatorSystem(new FloorSpan(1, 7));
		system.process();
	}

}
