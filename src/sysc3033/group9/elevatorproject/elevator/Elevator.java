package sysc3033.group9.elevatorproject.elevator;

import java.util.ArrayList;
import java.util.List;

import sysc3033.group9.elevatorproject.constants.elevator.DoorStatus;
import sysc3033.group9.elevatorproject.constants.elevator.MotorStatus;
import sysc3033.group9.elevatorproject.floor.FloorSpan;
import sysc3033.group9.elevatorproject.util.ButtonLamp;

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
		motor = new Motor(MotorStatus.IDLE);
		door = new Door(DoorStatus.CLOSED);
		buttons = new ArrayList<ElevatorButton>();
		for (int i = floorSpan.getMinFloorID(); i <= floorSpan.getMaxFloorID(); i++) {
			buttons.add(new ElevatorButton(i));
		}
	}

	/**
	 * Method to press the button on the elevator
	 * 
	 * @param targetFloor
	 */
	public void pressButton(int targetFloor) {
		for (ElevatorButton button : buttons) {
			if (button.getTargetFloor() == targetFloor) {
				// notify elevator system
				ButtonLamp lamp = button.getLamp();
				if (!lamp.isLit()) {
					lamp.switchStatus();
				}
			}
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
}
