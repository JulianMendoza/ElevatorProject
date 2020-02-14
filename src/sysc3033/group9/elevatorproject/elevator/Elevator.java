package sysc3033.group9.elevatorproject.elevator;

import java.util.ArrayList;
import java.util.List;

import sysc3033.group9.elevatorproject.constants.elevator.DoorStatus;
import sysc3033.group9.elevatorproject.constants.elevator.MotorStatus;
import sysc3033.group9.elevatorproject.floor.FloorSpan;

/**
 * Elevator class for the elevator system
 * 
 * @author Giuseppe Papalia
 *
 */
public class Elevator {

	private List<ElevatorButton> buttons;
	private Motor motor;
	private Door door;
	private int currentFloor;
	private boolean isLoaded;
	private boolean isBusy;

	/**
	 * Default constructor
	 * 
	 * @param floorSpan the span of the floors
	 */
	public Elevator(FloorSpan floorSpan) {
		createElevator(floorSpan);
	}

	/**
	 * Method to create the elevator
	 * 
	 * @param floorSpan
	 */
	private void createElevator(FloorSpan floorSpan) {
		isLoaded = false;
		motor = new Motor(MotorStatus.IDLE);
		door = new Door(DoorStatus.CLOSED);
		currentFloor = floorSpan.getMinFloorID();
		buttons = new ArrayList<ElevatorButton>();
		for (int i = floorSpan.getMinFloorID(); i <= floorSpan.getMaxFloorID(); i++) {
			buttons.add(new ElevatorButton(i));
		}
	}

	public void incrementFloor() {
		switch (motor.getStatus()) {
		case DOWN:
			currentFloor--;
		case IDLE:
			break;
		case UP:
			currentFloor++;
		default:
			break;

		}
	}

	/*
	 * getters door and motor
	 */
	public Door getDoor() {
		return door;
	}

	public Motor getMotor() {
		return motor;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public synchronized boolean isLoaded() {
		return isLoaded;
	}

	public synchronized void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}

	public synchronized boolean isBusy() {
		return isBusy;
	}

	public synchronized void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}

	@Override
	public int hashCode() {
		return (int) (isLoaded ? isBusy : currentFloor) * 17;
	}
}
