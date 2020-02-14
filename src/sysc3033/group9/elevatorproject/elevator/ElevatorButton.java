package sysc3033.group9.elevatorproject.elevator;

import sysc3033.group9.elevatorproject.util.Button;

/**
 * ElevatorButton class inherits from the Button class
 * 
 * @author Giuseppe Papalia
 *
 */
public final class ElevatorButton extends Button {

	private int targetFloor;

	/**
	 * Default constructor
	 * 
	 * @param targetFloor an integer where the floor will go
	 */
	public ElevatorButton(int targetFloor) {
		super();
		this.targetFloor = targetFloor;
	}

	/**
	 * Method to return the target floor
	 * 
	 * @return int
	 */
	public int getTargetFloor() {
		return targetFloor;
	}

}
