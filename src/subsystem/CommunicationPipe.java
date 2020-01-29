package subsystem;

import subsystem.floor.FloorEvent;

public class CommunicationPipe {
	private boolean floorEventNotifcation, promptFloor, elevatorEventNotification, promptElevator;
	private FloorEvent event;

	public CommunicationPipe() {
		floorEventNotifcation = false;
		promptFloor = false;
		elevatorEventNotification = false;
		promptElevator = false;
	}

	/**
	 * Communication from floor to scheduler
	 * 
	 * @param e
	 */
	public synchronized void handleFloorEvent(FloorEvent e) {
		event = e;
		floorEventNotifcation = true;
		notifyAll();
	}

	/**
	 * Communication between scheduler to floor
	 */
	public synchronized void schedulerToFloor() {
		promptFloor = true;
		notifyAll();
	}

	/**
	 * Communication from scheduler to elevator
	 */
	public synchronized void sendToElevator() {
		floorEventNotifcation = false;
		promptElevator = true;
		notifyAll();
	}

	/**
	 * Communication from elevator to scheduler
	 */
	public synchronized void elevatorToFloor() {
		elevatorEventNotification = true;
		notifyAll();
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

	public void setElevatorPrompt(boolean receivedEvent) {
		promptElevator = receivedEvent;
	}

	public void setElevatorEventNotifcation(boolean receivedEvent) {
		elevatorEventNotification = receivedEvent;
	}

	public void setFloorPrompt(boolean receivedEvent) {
		promptFloor = receivedEvent;
	}
}
