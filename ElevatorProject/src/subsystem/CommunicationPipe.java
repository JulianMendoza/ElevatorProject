package subsystem;

import subsystem.floor.FloorEvent;

public class CommunicationPipe {
	private boolean floorEventNotifcation,promptFloor,elevatorEventNotification,promptElevator;
	private FloorEvent event;
	public CommunicationPipe() {
		floorEventNotifcation=false;
		promptFloor=false;
		elevatorEventNotification=false;
		promptElevator=false;
	}
	public synchronized void addToQueue() {
		
	}
	/**
	 *  Communication from floor to scheduler
	 * @param e
	 */
	public synchronized void processFloorEvent(FloorEvent e) {
		event=e;
	}
	/**
	 * Communication between scheduler to floor
	 */
	public synchronized void schedulerToFloor() {
		//do stuff
	}
	/**
	 * Communication from scheduler to elevator
	 */
	public synchronized void processElevatorEvent() {
		//do stuff
	}
	/**
	 * Communication from elevator to scheduler
	 */
	public synchronized void elevatorToFloor() {
		//do stuff
	}
	public boolean isFloorEventNotifcation() {
		return floorEventNotifcation;
	}
	public boolean isPromptFloor() {
		return promptFloor;
	}
	public boolean isElevatorEventNotification() {
		return elevatorEventNotification;
	}
	public boolean isPromptElevator() {
		return promptElevator;
	}
	public FloorEvent getFloorEvent() {
		return event;
	}
}
