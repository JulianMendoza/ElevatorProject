package sysc3033.group9.elevatorproject.constants.floor;

import java.text.ParseException;

public enum FloorButton {
	
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
	
	public static FloorButton parse(String button) throws ParseException {
		button = button.toLowerCase();

		switch(button) {
		case "up":
			return UP;
		case "down":
			return DOWN;
		default:
			throw new ParseException("Invalid button", 0);
		}
	}
}
