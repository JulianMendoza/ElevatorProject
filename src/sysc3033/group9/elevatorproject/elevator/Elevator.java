package sysc3033.group9.elevatorproject.elevator;

import java.util.HashMap;
import java.util.Map;

import sysc3033.group9.elevatorproject.constants.elevator.DoorStatus;
import sysc3033.group9.elevatorproject.constants.elevator.MotorStatus;
import sysc3033.group9.elevatorproject.floor.FloorSpan;

public class Elevator {

	private Map<ElevatorButton, ElevatorLamp> buttons;
	private Motor motor;
	private Door door;

	public Elevator(FloorSpan floorSpan) {
		createElevator(floorSpan);
	}

	private void createElevator(FloorSpan floorSpan) {
		motor = new Motor(MotorStatus.IDLE);
		door = new Door(DoorStatus.CLOSED);
		buttons = new HashMap<ElevatorButton, ElevatorLamp>();
		for (int i = floorSpan.getMinFloorID(); i <= floorSpan.getMaxFloorID(); i++) {
			buttons.put(new ElevatorButton(i), new ElevatorLamp(false));
		}
	}

	public Door getDoor() {
		return door;
	}

	public Motor getMotor() {
		return motor;
	}

	public void pressButton(int targetFloor) {
		for (ElevatorButton button : buttons.keySet()) {
			if (button.getTargetFloor() == targetFloor) {
				// notify elevator system
				ElevatorLamp lamp = buttons.get(button);
				if (!lamp.isLit()) {
					lamp.switchStatus();
				}
			}
		}
	}
}
