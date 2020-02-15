package sysc3033.group9.elevatorproject.system;

import java.util.LinkedList;
import java.util.List;

import sysc3033.group9.elevatorproject.GUI.SystemView;
import sysc3033.group9.elevatorproject.constants.SleepTime;
import sysc3033.group9.elevatorproject.constants.elevator.MotorStatus;
import sysc3033.group9.elevatorproject.elevator.Elevator;
import sysc3033.group9.elevatorproject.event.FloorEvent;
import sysc3033.group9.elevatorproject.floor.FloorSpan;
import sysc3033.group9.elevatorproject.util.Sleeper;

/**
 * ElevatorSystem thread Handles events that the elevators must process
 * 
 * @author Julian Mendoza, Giuseppe Papalia
 *
 */
public class ElevatorSystem implements Runnable {
	private CommunicationPipe pipe;
	private boolean isMoving;
	private int currentFloor, floorThreshold;
	private Elevator elevator;
	private SystemView view;
	private LinkedList<Integer> requests, designations;
	private List<FloorEvent> eventQueue;
	private boolean takingRequests, takingDesignations;

	/**
	 * Default constructor
	 * 
	 * @param floorSpan the span of the elevator
	 * @param pipe      the communication pipe
	 * @param view      GUI
	 */
	public ElevatorSystem(FloorSpan floorSpan, CommunicationPipe pipe, SystemView view) {
		this.elevator = new Elevator(floorSpan);
		this.currentFloor = floorSpan.getMinFloorID();
		this.pipe = pipe;
		this.isMoving = false;
		this.view = view;
		this.requests = new LinkedList<Integer>();
		this.designations = new LinkedList<Integer>();
		this.eventQueue = new LinkedList<FloorEvent>();
		this.floorThreshold = -1;
		this.takingDesignations = false;
	}

	@Override
	public void run() {
		while (true) {
			if (pipe.isSchedulerToElevator()) {
				handleElevatorEvent();
			} else if (isMoving) {
				if (currentFloor != floorThreshold) {
					handleMove();
				} else {
					isMoving = false;
				}
			} else {
				view.setQueue(view.getQueueText(), "NONE IN QUEUE");
			}
			Sleeper.sleep(SleepTime.DEFAULT);
		}
	}

	/**
	 * Method to handle an elevatorEvent
	 */
	private void handleElevatorEvent() {
		view.setText(view.getElevatorText(),
				Thread.currentThread().getName() + " has received the signal about a new event.\n");
		eventQueue.add(pipe.getEvent());
		FloorEvent e = eventQueue.remove(0);
		if (!requests.contains(e.getFloor())) {
			requests.add(e.getFloor());
		}
		if (!requests.contains(e.getElevatorCarID())) {
			designations.add(e.getElevatorCarID());
		}
		if (!isMoving) {
			takingRequests = true;
			isMoving = true;
			setInitialStatus(e);
		}
		MotorStatus status = elevator.getMotor().getStatus();
		view.setText(view.getDisplayText(), currentFloor + " " + status + "\n");
		pipe.setSchedulerToElevator(false);
	}

	/**
	 * Method to set the elevator status if it is not moving
	 * 
	 * @param e The floor event
	 */
	private void setInitialStatus(FloorEvent e) {
		if (e.getFloor() - currentFloor > 0) {
			elevator.getMotor().setStatus(MotorStatus.UP);
		} else if (e.getFloor() - currentFloor > 0) {
			elevator.getMotor().setStatus(MotorStatus.DOWN);
		}
	}

	/**
	 * Method to handle movements of the elevators
	 */
	private void handleMove() {
		MotorStatus motorStatus = elevator.getMotor().getStatus();
		Sleeper.sleep(SleepTime.FLOOR);
		String str = "";
		if (!motorStatus.equals(MotorStatus.IDLE)) { // Idle if the event occurs on the current floor bug prone
			if (motorStatus.equals(MotorStatus.UP)) {
				currentFloor++;
				str += "The Elevator has moved up to floor " + currentFloor;
			} else {
				currentFloor--;
				str += "The Elevator has moved down to floor " + currentFloor;
			}
			// view.setText(view.getDisplayText(), currentFloor + " " +
			// elevator.getMotor().getStatus() + "\n");
			announce(str, currentFloor + " " + elevator.getMotor().getStatus() + "\n");
		} else {
			promptDoor();
		}
		if (takingRequests && requests.contains(currentFloor)) { // a floor has been reached where a pickup is required
			requests.remove(0); // bug if the current floor is not the first in the queue lol
			if (requests.isEmpty()) {
				takingRequests = false;
				takingDesignations = true;
			}
			promptDoor();
			// determine the status based off of where to go next
			MotorStatus newStatus = (designations.peek() - currentFloor > 0) ? MotorStatus.UP : MotorStatus.DOWN;
			elevator.getMotor().setStatus(newStatus);
		} else if (takingDesignations && designations.contains(currentFloor)) { // same as above but for designations
			designations.remove(0);
			if (designations.isEmpty()) {
				takingDesignations = false;
			}
			promptDoor();
			if (!requests.isEmpty()) {
				takingRequests = true;
				MotorStatus newStatus = (requests.peek() - currentFloor > 0) ? MotorStatus.UP : MotorStatus.DOWN;
				elevator.getMotor().setStatus(newStatus);
			} else {
				elevator.getMotor().setStatus(MotorStatus.IDLE);
				isMoving = false;
			}
		}

	}

	/**
	 * Helper method to prompt the doors once a floor has been reached
	 */
	private void promptDoor() {
		view.setText(view.getElevatorText(), "The Elevator has reached the Floor!\nOpening the door...\n");
		Sleeper.sleep(SleepTime.LOAD);
		view.setText(view.getElevatorText(), "DOOR OPENED!\nClosing the door...\n");
		Sleeper.sleep(SleepTime.LOAD);
		view.setText(view.getElevatorText(), "DOOR CLOSED!\n");
	}

	/**
	 * Method to announce a change in the display lamps
	 * 
	 * @param msg the message to be announced as a string
	 */
	private void announce(String msg, String displayLamp) {
		view.setText(view.getElevatorText(),
				msg + "\n" + Thread.currentThread().getName() + " has signaled the lamps to the Scheduler.\n");
		pipe.elevatorToScheduler();
		pipe.setLamp(displayLamp);
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public Elevator getElevator() {
		return elevator;
	}

}
