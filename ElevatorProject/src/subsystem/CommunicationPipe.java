package subsystem;

import subsystem.floor.FloorEvent;

public class CommunicationPipe {
	private boolean floorEventNotifcation;
	private boolean promptFloor;
	private boolean elevatorEventNotification;
	private boolean promptElevator;
	
	public synchronized void addToQueue() {
		
	}
	public synchronized void processFloorEvent(FloorEvent e) {
		
	}
}
