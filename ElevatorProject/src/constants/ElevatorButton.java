package constants;

import java.text.ParseException;

public enum ElevatorButton {
	
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
	
	public static ElevatorButton parse(String button) throws ParseException {
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
