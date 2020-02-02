package sysc3033.group9.elevatorproject.elevator;

import sysc3033.group9.elevatorproject.constants.elevator.DoorStatus;

/**
 * Door object for the elevator
 * 
 * @author Giuseppe Papalia
 *
 */
public class Door {

	private DoorStatus status;

	/**
	 * Constructor for the door
	 * 
	 * @param initialStatus a DoorStatus enum
	 */
	public Door(DoorStatus initialStatus) {
		status = initialStatus;
	}

	/**
	 * Switch status of the door
	 */
	public void switchStatus() {
		status = status.switchStatus();
	}

	/**
	 * Getter method for the status
	 * 
	 * @return a DoorStatus Enum of the status
	 */
	public DoorStatus getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Door is " + status.toString().toLowerCase();
	}
}
