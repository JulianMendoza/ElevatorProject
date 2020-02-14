package sysc3033.group9.elevatorproject.system;

import sysc3033.group9.elevatorproject.GUI.SystemView;
import sysc3033.group9.elevatorproject.constants.SleepTime;
import sysc3033.group9.elevatorproject.event.FloorEvent;
import sysc3033.group9.elevatorproject.util.Sleeper;

/**
 * Scheduler Thread handles the communication between the elevator and floor
 * subsystem
 * 
 * @author Julian Mendoza
 *
 */
public class Scheduler implements Runnable {
	private CommunicationPipe pipe;
	private FloorEventQueue eventQueue;
	private SystemView view;

	/**
	 * Default constructor of the Scheduler
	 * 
	 * @param pipe The communication pipe
	 * @param view GUI view
	 */
	public Scheduler(FloorEventQueue eventQueue, CommunicationPipe pipe, SystemView view) {
		this.eventQueue = eventQueue;
		this.pipe = pipe;
		this.view = view;
	}

	/**
	 * Method to handle the floor events
	 * 
	 * @param e the FloorEvent passed
	 */
	public void handleFloorEvent(FloorEvent e) {
		eventQueue.add(e);
		view.setText(view.getSchedulerText(),
				Thread.currentThread().getName() + " has received the signal.\nA user on floor #" + e.getFloor()
						+ " wants to go " + e.getElevatorButton() + " to floor #" + e.getElevatorCarID()
						+ "\nNotifying the elevator\n");
		pipe.sendToElevator(eventQueue.getQueue());
	}

	/**
	 * Method to handle the elevator events
	 */
	public void handleElevatorEvent() {
		view.setText(view.getSchedulerText(), Thread.currentThread().getName()
				+ " has received elevators notifcation and is now notifying the floor\n");
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
