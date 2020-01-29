package sysc3033.group9.elevatorproject.constants.elevator;

public enum DoorStatus {

	OPEN, CLOSED;

	public String toString() {
		switch(this) {
		case OPEN:
			return "Open";
		case CLOSED:
			return "CLOSED";
		default:
			return null;
		
		}
	}
	
	public DoorStatus switchStatus() {
		switch(this) {
		case CLOSED:
			return OPEN;
		case OPEN:
			return CLOSED;
		default:
			return null;
		
		}
	}
}
