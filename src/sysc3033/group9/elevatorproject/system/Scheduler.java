package sysc3033.group9.elevatorproject.system;

import java.util.ArrayList;

import sysc3033.group9.elevatorproject.GUI.SystemView;
import sysc3033.group9.elevatorproject.constants.SleepTime;
import sysc3033.group9.elevatorproject.event.FloorEvent;
import sysc3033.group9.elevatorproject.util.Sleeper;

/**
 * Scheduler Thread handles the communication between the elevator and floor subsystem
 * 
 * @author Julian Mendoza
 *
 */
public class Scheduler implements Runnable {
	private CommunicationPipe pipe;
	private ArrayList<FloorEvent> elevatorQueue;
	private SystemView view;

	/**
	 * Default constructor of the Scheduler
	 * 
	 * @param pipe The communication pipe
	 * @param view GUI view
	 */
	public Scheduler(CommunicationPipe pipe, SystemView view) {
		this.pipe = pipe;
		this.view = view;
		elevatorQueue = new ArrayList<FloorEvent>();
	}

	/**
	 * Method to handle the floor events
	 * 
	 * @param e the FloorEvent passed
	 */
	public void handleFloorEvent(FloorEvent e) {
		/*
		 * TODO process the event, create a queue to let the elevator know where to go next logical flow: is elevator moving in the direction of the floor, is there currently a queue etc
		 */
		int[] event = new int[] { e.getFloor(), e.getElevatorCarID() };
		/*
		 * if (elevatorQueue.isEmpty()) { elevatorQueue.add(floorMap); } else { determinePositionInQueue(floorMap); }
		 */
		elevatorQueue.add(e);
		System.out.println(Thread.currentThread().getName() + " has received the signal.\nA user on floor #" + e.getFloor() + " wants to go " + e.getElevatorButton() + " to floor #" + e.getElevatorCarID() + "\nNotifying the elevator");
		view.setText(view.getSchedulerText(), Thread.currentThread().getName() + " has received the signal.\nA user on floor #" + e.getFloor() + " wants to go " + e.getElevatorButton() + " to floor #" + e.getElevatorCarID() + "\nNotifying the elevator\n");
		pipe.sendToElevator(event);
	}

	/*
	 * private void determinePositionInQueue(Map.Entry<Integer, Integer> floorMap) { if (!elevatorQueue.contains(floorMap)) {
	 * 
	 * } }
	 */
	/**
	 * Method to handle the elevator events
	 */
	public void handleElevatorEvent() {
		System.out.println(Thread.currentThread().getName() + " has received elevators notifcation and is now notifying the floor");
		view.setText(view.getSchedulerText(), Thread.currentThread().getName() + " has received elevators notifcation and is now notifying the floor\n");
		pipe.setElevatorToScheduler(false);
		pipe.schedulerToFloor();
	}

	@Override
	public void run() {
		while (true) {
			if (pipe.isFloorToScheduler()) {
				handleFloorEvent(pipe.getFloorEvent());
			}
			if (pipe.isElevatorToScheduler()) {
				handleElevatorEvent();
			}
			Sleeper.sleep(SleepTime.DEFAULT);
		}
	}

}
