package sysc3033.group9.elevatorproject.constants;

import java.text.ParseException;

public enum Direction {

	UP, DOWN, IDLE;

	@Override
	public String toString() {
		switch (this) {
		case DOWN:
			return "Down";
		case UP:
			return "Up";
		case IDLE:
			return "Idle";
		default:
			return null;

		}
	}

	public static Direction parse(String direction) throws ParseException {
		direction = direction.toLowerCase();

		switch (direction) {
		case "up":
			return UP;
		case "down":
			return DOWN;
		case "idle":
			return IDLE;
		default:
			throw new ParseException("Invalid direction", 0);
		}
	}
}
