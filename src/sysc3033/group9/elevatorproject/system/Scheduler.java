package sysc3033.group9.elevatorproject.system;

import sysc3033.group9.elevatorproject.event.FloorEvent;
import sysc3033.group9.elevatorproject.util.Sleeper;

public class Scheduler implements Runnable {
	private CommunicationPipe pipe;

	public Scheduler(CommunicationPipe pipe) {
		this.pipe = pipe;
	}

	public void handleFloorEvent(FloorEvent e) {
		/*
		 * TODO process the event, create a queue to let the elevator know where to go
		 * next logical flow: is elevator moving in the direction of the floor, is there
		 * currently a queue etc
		 */
		System.out.println(Thread.currentThread().getName() + " has received the signal.\nA user on floor #"
				+ e.getFloor() + " wants to go " + e.getElevatorButton() + " to floor #" + e.getElevatorCarID()
				+ "\nNotifying the elevator");
		pipe.sendToElevator(new int[] { e.getFloor(), e.getElevatorCarID() });
	}

	public void handleElevatorEvent() {
		System.out.println(Thread.currentThread().getName()
				+ " has received elevators notifcation and is now notifying the floor");
		pipe.setElevatorToFloor(false);
		pipe.schedulerToFloor();
	}

	@Override
	public void run() {
		while (true) {
			if (pipe.isFloorToScheduler()) {
				handleFloorEvent(pipe.getFloorEvent());
			} else if (pipe.isElevatorToScheduler()) {
				handleElevatorEvent();
			}
			Sleeper.sleep(500);
		}
	}

}
