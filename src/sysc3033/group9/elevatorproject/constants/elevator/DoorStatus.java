package sysc3033.group9.elevatorproject.constants.elevator;

public enum DoorStatus {

	OPEN, CLOSED;

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
