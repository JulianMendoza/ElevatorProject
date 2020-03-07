package sysc3033.group9.elevatorproject.constants;

import java.io.Serializable;
import java.text.ParseException;

/**
 * Direction Enum UP, DOWN and IDLE
 * 
 * @author Giuseppe Papalia
 *
 */
public enum Direction implements Serializable {

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

	/**
	 * Method to parse string into a direction
	 * 
	 * @param direction string of the direction
	 * @return the Direction Enum
	 * @throws ParseException when invalid
	 */
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
