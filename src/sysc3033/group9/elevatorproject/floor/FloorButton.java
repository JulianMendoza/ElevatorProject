package sysc3033.group9.elevatorproject.floor;

import sysc3033.group9.elevatorproject.constants.Direction;
import sysc3033.group9.elevatorproject.util.Button;

/**
 * FloorButton represents the button on the elevator to choose the direction you want to go
 *
 * @author Giuseppe Papalia
 * @documentation Kelly Harrison
 */
public class FloorButton extends Button {

	private Direction targetDirection;

	/**
	 * Constructor for FloorButton class
	 * 
	 * @param targetDirection is the direction (up/ down)
	 */
	public FloorButton(Direction targetDirection) {
		super();
		this.targetDirection = targetDirection;
	}

	/**
	 * getter for the direction
	 * 
	 * @return the direction (UP/DOWN)
	 */
	public Direction getTargetDirection() {
		return targetDirection;
	}

}
