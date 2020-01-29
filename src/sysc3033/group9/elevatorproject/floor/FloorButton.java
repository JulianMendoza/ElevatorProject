package sysc3033.group9.elevatorproject.floor;

import sysc3033.group9.elevatorproject.constants.Direction;
import sysc3033.group9.elevatorproject.util.Button;

public class FloorButton extends Button {

	private Direction targetDirection;

	public FloorButton(Direction targetDirection) {
		super();
		this.targetDirection = targetDirection;
	}

	public Direction getTargetDirection() {
		return targetDirection;
	}

}
