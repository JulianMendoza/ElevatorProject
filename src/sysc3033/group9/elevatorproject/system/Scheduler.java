package sysc3033.group9.elevatorproject.system;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Scheduler Thread handles the communication between the elevator and floor
 * subsystem
 * 
 * @author Julian Mendoza, Giuseppe Papalia
 *
 */
public class Scheduler {
	private ElevatorSystem elevatorSystem;
	private Schedule schedule;
	private int PORT_NUMBER = 4444;
	private DatagramSocket client, server;
	private Thread clientToServer, serverToClient;

	public Scheduler(Schedule schedule) {
		this.schedule = schedule;
		try {
			client = new DatagramSocket(PORT_NUMBER);
			server = new DatagramSocket(4445);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public Scheduler(Schedule schedule, ElevatorSystem elevatorSystem) {
		this(schedule);
		this.elevatorSystem = elevatorSystem;
	}
	/*
	 * public void scheduleEvent(FloorEvent e) { elevatorSystem.scheduleEvent(e, 0);
	 * // always use car 0 for now }
	 */

	private void process() throws UnknownHostException {
		clientToServer = new Thread(new ClientToServer(client, server), "ClientToServer");
		serverToClient = new Thread(new ServerToClient(client, server), "ServerToClient");
		clientToServer.run();
		serverToClient.run();
	}

	public static void main(String[] args) throws UnknownHostException {
		Scheduler scheduler = new Scheduler(new Schedule());
		scheduler.process();
	}
}
