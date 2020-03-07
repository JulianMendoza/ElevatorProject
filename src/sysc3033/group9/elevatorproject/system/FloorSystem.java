package sysc3033.group9.elevatorproject.system;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import sysc3033.group9.elevatorproject.constants.FilePath;
import sysc3033.group9.elevatorproject.event.EventFile;
import sysc3033.group9.elevatorproject.event.FloorEvent;
import sysc3033.group9.elevatorproject.floor.Floor;
import sysc3033.group9.elevatorproject.floor.FloorSpan;
import sysc3033.group9.elevatorproject.util.Parser;

/**
 * FloorSubSystem class contains the knowledge of all floors. Button presses and
 * display lamps are updated here
 * 
 * @author Julian Mendoza, Giuseppe Papalia
 *
 */
public class FloorSystem {

	private List<Floor> floors;
	private Schedule schedule;
	private EventFile eventFile;
	private int PORTNUMBER = 3333; // move to util folder
	private DatagramSocket socket;
	private DatagramPacket sendPacket, receivePacket;

	/**
	 * Constructor of the floor system
	 * 
	 * @param floorSpan The span of the number of floors
	 * @param schedule  A pipe which will communicate nessacary events
	 * @param eventFile System file which is polled to check if a new event has
	 *                  occured
	 * @param view      GUI
	 */
	public FloorSystem(FloorSpan floorSpan, Schedule schedule, EventFile eventFile) {
		createFloors(floorSpan);
		this.schedule = schedule;
		this.eventFile = eventFile;
		try {
			socket = new DatagramSocket(PORTNUMBER);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the floors
	 * 
	 * @param floorSpan span of the number of floors
	 */
	private void createFloors(FloorSpan floorSpan) {
		floors = new ArrayList<Floor>();

		for (int i = floorSpan.getMinFloorID(); i <= floorSpan.getMaxFloorID(); i++) {
			floors.add(new Floor(i, i == floorSpan.getMinFloorID(), i == floorSpan.getMaxFloorID()));
		}
	}

	private void signal() {
		FloorEvent e = Parser.readTextFile(FilePath.EVENT_FILE, eventFile.getFile());
		// view.setText(view.getFloorText(), Thread.currentThread().getName() + " has
		// received an event.\nFloor #"
		// + e.getFloor() + " was pressed.\nSignaling to Scheduler\n");
		schedule.add(e);
	}

	private void process() throws IOException {
		String s = "Hello from floor";
		String s2 = "Give me data";
		sendPacket = new DatagramPacket(s.getBytes(), s.getBytes().length, InetAddress.getLocalHost(), 4444);
		socket.send(sendPacket);
		receivePacket = new DatagramPacket(new byte[1024], 1024);
		socket.receive(receivePacket);
		System.out.println(new String(receivePacket.getData()));
		sendPacket = new DatagramPacket(s2.getBytes(), s2.getBytes().length, InetAddress.getLocalHost(), 4444);
		socket.send(sendPacket);
		socket.receive(receivePacket);
		System.out.println("Got some data");
		System.out.println(new String(receivePacket.getData()));
	}

	public static void main(String[] args) throws IOException {
		FloorSystem floor = new FloorSystem(new FloorSpan(1, 7), new Schedule(), new EventFile());
		floor.process();
	}

}