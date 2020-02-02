package sysc3033.group9.elevatorproject.constants.elevator;

/**
 * DoorStatus Enum OPEN when open, CLOSED when closed
 * 
 * @author Giuseppe Papalia
 *
 */
public enum DoorStatus {

	OPEN, CLOSED;

	/**
	 * toString
	 */
	@Override
	public String toString() {
		switch (this) {
		case OPEN:
			return "Open";
		case CLOSED:
			return "Closed";
		default:
			return null;

		}
	}

	/**
	 * method to switch status
	 * 
	 * @return the opposite enum
	 */
	public DoorStatus switchStatus() {
		switch (this) {
		case CLOSED:
			return OPEN;
		case OPEN:
			return CLOSED;
		default:
			return null;

		}
	}
}
