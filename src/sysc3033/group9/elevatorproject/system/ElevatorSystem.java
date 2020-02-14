package sysc3033.group9.elevatorproject.system;

import java.util.ArrayList;
import java.util.List;

import sysc3033.group9.elevatorproject.GUI.SystemView;
import sysc3033.group9.elevatorproject.constants.Direction;
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
	private List<FloorEvent> eventQueue;
	private ArrayList<Integer> stops;

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
		this.stops = new ArrayList<Integer>();
		this.floorThreshold = -1;
	}

	@Override
	public void run() {
		while (true) {
			if (pipe.isSchedulerToElevator()) {
				handleElevatorEvent();
			} else if (isMoving) {
				handleMove();
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
		if (eventQueue == null) {
			this.eventQueue = pipe.getEventQueue();
		}
		MotorStatus status = elevator.getMotor().getStatus();
		if (status.equals(MotorStatus.IDLE)) {
			FloorEvent e = eventQueue.remove(0);
			setStatus(e);
			floorThreshold = e.getFloor();
			addStops(e);
			isMoving = true;
		} else {
			FloorEvent e = eventQueue.get(0);
			int check = e.getElevatorCarID() - e.getFloor();
			if (check < 0 && status.equals(MotorStatus.DOWN)) {
				if (floorThreshold == -1 || e.getElevatorCarID() < floorThreshold) {
					floorThreshold = e.getElevatorCarID();
					addStops(e);
					eventQueue.remove(0);
				}
			} else if (check > 0 && status.equals(MotorStatus.UP)) {
				if (floorThreshold == -1 || e.getElevatorCarID() > floorThreshold) {
					floorThreshold = e.getElevatorCarID();
					addStops(e);
					eventQueue.remove(0);
				}
			}
		}
		view.setText(view.getDisplayText(), currentFloor + " " + status + "\n");
		pipe.setSchedulerToElevator(false);
	}

	private void setStatus(FloorEvent e) {
		if (e.getFloor() - currentFloor > 0) {
			elevator.getMotor().setStatus(MotorStatus.UP);
		} else {
			elevator.getMotor().setStatus(MotorStatus.DOWN);
		}
	}

	private void addStops(FloorEvent e) {
		stops.add(e.getElevatorCarID());
		stops.add(e.getFloor());
	}

	/**
	 * Method to handle movements of the elevators
	 */
	private void handleMove() {
		MotorStatus motorStatus = elevator.getMotor().getStatus();
		Sleeper.sleep(SleepTime.FLOOR);
		String str = "";
		if (motorStatus.equals(MotorStatus.UP)) {
			currentFloor++;
			str += "The Elevator has moved up to floor " + currentFloor;
		} else {
			currentFloor--;
			str += "The Elevator has moved down to floor " + currentFloor;
		}
		if (stops.contains(currentFloor)) {
			int index = stops.indexOf(currentFloor);
			stops.remove(index);
			if (currentFloor == floorThreshold) {
				elevator.getMotor().setStatus(MotorStatus.IDLE);
			}
			promptDoor();
		}
		view.setText(view.getDisplayText(), currentFloor + " " + motorStatus + "\n");
		announce(str);
		/*
		 * int[] moveEvent = pipe.getNextInQueue(); //
		 * view.setQueue(view.getQueueText(), "[ " + moveEvent[0] + " , " + moveEvent[1]
		 * // + " ]"); if ((moveEvent[0] - currentFloor) != 0) { if ((moveEvent[0] -
		 * currentFloor) > 0) { elevatorMotor.setStatus(MotorStatus.UP);
		 * move(Direction.UP, moveEvent[0] - currentFloor); } else {
		 * elevatorMotor.setStatus(MotorStatus.DOWN); move(Direction.DOWN, currentFloor
		 * - moveEvent[0]); } } if ((moveEvent[1] - currentFloor) > 0) {
		 * elevatorMotor.setStatus(MotorStatus.UP); move(Direction.UP, moveEvent[1] -
		 * currentFloor); } else { elevatorMotor.setStatus(MotorStatus.DOWN);
		 * move(Direction.DOWN, currentFloor - moveEvent[1]); }
		 * elevatorMotor.setStatus(MotorStatus.IDLE); isMoving = false;
		 */
	}

	private void promptDoor() {
		view.setText(view.getElevatorText(), "The Elevator has reached the Floor!\nOpening the door...\n");
		Sleeper.sleep(SleepTime.LOAD);
		view.setText(view.getElevatorText(), "DOOR OPENED!\nClosing the door...\n");
		Sleeper.sleep(SleepTime.LOAD);
		view.setText(view.getElevatorText(), "DOOR CLOSED!\n");
	}

	/**
	 * Helper function to move the elevators
	 * 
	 * @param direction The direction of the elevator
	 * @param steps     The amount of floors the elevator must move
	 */
	@Deprecated
	private void move(Direction direction, int steps) {
		for (int i = 0; i < steps; i++) {
			Sleeper.sleep(SleepTime.FLOOR);
			String str = "";
			if (direction.equals(Direction.UP)) {
				currentFloor++;
				str += "The Elevator has moved up to floor " + currentFloor;
			} else {
				currentFloor--;
				str += "The Elevator has moved down to floor " + currentFloor;
			}
			view.setText(view.getDisplayText(), currentFloor + " " + direction + "\n");
			announce(str);
		}
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
	private void announce(String msg) {
		view.setText(view.getElevatorText(),
				msg + "\n" + Thread.currentThread().getName() + " has signaled the lamps to the Scheduler.\n");
		pipe.elevatorToScheduler();
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public Elevator getElevator() {
		return elevator;
	}

}
