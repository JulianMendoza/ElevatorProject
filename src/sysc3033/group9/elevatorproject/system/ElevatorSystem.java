package sysc3033.group9.elevatorproject.system;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

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

	Map<Integer, Elevator> elevators;
	FloorEventQueue eventQueue;
	private DatagramSocket socket;
	private DatagramPacket requestPacket, dataPacket, ackPacket;
	private InetAddress IP;

	public ElevatorSystem(FloorSpan floorSpan) {
		try {
			socket = new DatagramSocket(5555);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			IP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Default constructor
	 * 
	 * @param floorSpan the span of the elevator
	 * @param pipe      the communication pipe
	 * @param view      GUI
	 */
	public ElevatorSystem(FloorSpan floorSpan, int numElevators) {
		elevators = new HashMap<Integer, Elevator>();
		for (int i = 0; i < numElevators; i++) {
			elevators.put(i, new Elevator(floorSpan));
		}
		eventQueue = new FloorEventQueue(elevators);
	}

	/**
	 * 
	 * 
	 * @Override public void run() { while (true) { Map<Elevator, FloorEvent>
	 *           priorityEvents = eventQueue.removePriorityEvents(); for (Elevator
	 *           elevator : priorityEvents.keySet()) {
	 *           handleFloorEvent(priorityEvents.get(elevator), elevator); }
	 *           Sleeper.sleep(SleepTime.DEFAULT); } }
	 */

	public synchronized void scheduleEvent(FloorEvent e, int elevatorCarID) {
		eventQueue.add(e, elevators.get(elevatorCarID));
	}

	private void handleFloorEvent(FloorEvent e, Elevator elevator) {
		ElevatorEvent e2 = createElevatorEvent(e, elevator);
		dispatchElevatorEvent(e2);
	}

	private ElevatorEvent createElevatorEvent(FloorEvent e, Elevator elevator) {
		return new ElevatorEvent(elevator, e.getFloor());
	}

	private void dispatchElevatorEvent(ElevatorEvent e) {
		Thread elevatorEvent = new Thread(e);
		elevatorEvent.start();
	}

	private void process() {
		String s = "Request from the elevator";
		String s2 = "Processed data is here";
		requestPacket = new DatagramPacket(s.getBytes(), s.getBytes().length, IP, 4445);
		try {
			socket.send(requestPacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataPacket = new DatagramPacket(new byte[1024], 1024);
		try {
			socket.receive(dataPacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Received a packet");
		System.out.println(new String(dataPacket.getData()));
		// process data
		ackPacket = new DatagramPacket(s2.getBytes(), s2.getBytes().length, IP, 4445);
		try {
			socket.send(ackPacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataPacket = new DatagramPacket(new byte[1024], 1024);
		try {
			socket.receive(dataPacket);
			System.out.println("closing connection");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ElevatorSystem system = new ElevatorSystem(new FloorSpan(1, 7));
		system.process();
	}

}
