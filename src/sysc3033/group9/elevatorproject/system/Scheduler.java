package sysc3033.group9.elevatorproject.system;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
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
	private MulticastSocket client, server;
	private Thread clientToServer, serverToClient;

	public Scheduler(Schedule schedule) {
		this.schedule = schedule;
		try {
			client = new MulticastSocket(PORT_NUMBER);
			server = new MulticastSocket(4445);
			client.joinGroup(InetAddress.getByName("225.6.7.8"));
			server.joinGroup(InetAddress.getByName("225.6.7.8"));
		} catch (IOException e) {
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
		System.setProperty("java.net.preferIPv4Stack", "true");
		Scheduler scheduler = new Scheduler(new Schedule());
		scheduler.process();
	}
}
