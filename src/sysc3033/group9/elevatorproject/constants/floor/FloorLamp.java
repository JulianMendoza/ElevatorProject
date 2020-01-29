package sysc3033.group9.elevatorproject.constants.floor;

public enum FloorLamp {

	UP, DOWN;
	

	public String toString() {
		switch(this) {
		case DOWN:
			return "Down";
		case UP:
			return "Up";
		default:
			return null;
		}
	}
	
}
