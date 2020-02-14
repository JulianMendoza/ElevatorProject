package sysc3033.group9.elevatorproject.system;

import java.util.List;

import sysc3033.group9.elevatorproject.GUI.SystemView;
import sysc3033.group9.elevatorproject.constants.Direction;
import sysc3033.group9.elevatorproject.constants.SleepTime;
import sysc3033.group9.elevatorproject.constants.elevator.MotorStatus;
import sysc3033.group9.elevatorproject.elevator.Elevator;
import sysc3033.group9.elevatorproject.elevator.Motor;
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
	private int currentFloor;
	private Elevator elevator;
	private SystemView view;
	private List<FloorEvent> eventQueue;

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
	}

	@Override
	public void run() {
		while (true) {
			if (pipe.isSchedulerToElevator()) {
				handleElevatorEvent();
			}
			if (isMoving) {
				handleMove();
			}
			Sleeper.sleep(SleepTime.DEFAULT);
			view.setQueue(view.getQueueText(), "NONE IN QUEUE");
		}
	}

	/**
	 * Method to handle an elevatorEvent
	 */
	private void handleElevatorEvent() {
		if (eventQueue == null) {
			if (eventQueue != pipe.getEventQueue()) {
				this.eventQueue = pipe.getEventQueue();
			}
		}
		MotorStatus status = elevator.getMotor().getStatus();
		view.setText(view.getDisplayText(), currentFloor + " " + status + "\n");
		if (status.equals(MotorStatus.IDLE)) {
			view.setText(view.getElevatorText(),
					Thread.currentThread().getName() + " has received the signal and is currently moving.\n");
			pipe.setSchedulerToElevator(false);
			isMoving = true;
		}
	}

	/**
	 * Method to handle movements of the elevators
	 */
	private void handleMove() {
		Motor elevatorMotor = elevator.getMotor();
		int[] moveEvent = pipe.getNextInQueue();
		view.setQueue(view.getQueueText(), "[ " + moveEvent[0] + " , " + moveEvent[1] + " ]");
		if ((moveEvent[0] - currentFloor) != 0) {
			if ((moveEvent[0] - currentFloor) > 0) {
				elevatorMotor.setStatus(MotorStatus.UP);
				move(Direction.UP, moveEvent[0] - currentFloor);
			} else {
				elevatorMotor.setStatus(MotorStatus.DOWN);
				move(Direction.DOWN, currentFloor - moveEvent[0]);
			}
		}
		if ((moveEvent[1] - currentFloor) > 0) {
			elevatorMotor.setStatus(MotorStatus.UP);
			move(Direction.UP, moveEvent[1] - currentFloor);
		} else {
			elevatorMotor.setStatus(MotorStatus.DOWN);
			move(Direction.DOWN, currentFloor - moveEvent[1]);
		}
		elevatorMotor.setStatus(MotorStatus.IDLE);
		isMoving = false;
	}

	/**
	 * Helper function to move the elevators
	 * 
	 * @param direction The direction of the elevator
	 * @param steps     The amount of floors the elevator must move
	 */
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
