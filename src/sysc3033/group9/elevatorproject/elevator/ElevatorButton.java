package sysc3033.group9.elevatorproject.elevator;

import sysc3033.group9.elevatorproject.util.Button;

public final class ElevatorButton extends Button {

	private int targetFloor;

	public ElevatorButton(int targetFloor) {
		super();
		this.targetFloor = targetFloor;
	}

	public int getTargetFloor() {
		return targetFloor;
	}

}
