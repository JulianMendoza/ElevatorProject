package sysc3033.group9.elevatorproject.event;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Random;

import sysc3033.group9.elevatorproject.constants.Direction;
import sysc3033.group9.elevatorproject.constants.FilePath;
import sysc3033.group9.elevatorproject.constants.FloorID;
import sysc3033.group9.elevatorproject.util.Parser;
import sysc3033.group9.elevatorproject.util.Time;

/**
 * FloorEvent class generates a new random floor event for the elevator
 * 
 * @author Giuseppe Papalia
 * @documentation Kelly Harrison
 *
 */
public class FloorEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Time time;
	private int floor;
	private Direction button;
	private int targetFloor;
	private static final Random RANDOM = new Random();

	/**
	 * Constructor for FloorEvent
	 */
	public FloorEvent() {
		createNewEvent();
	}

	/**
	 * Second constructor for FloorEvent
	 * 
	 * @param floorEvent is the string passed in of the floor information
	 */
	public FloorEvent(String floorEvent) throws ParseException {
		parse(floorEvent);
	}

	/**
	 * Third FloorEvent constructor
	 * 
	 * @param time        is the time passed in
	 * @param floor       is the floor number between the MAX and MIN values
	 * @param direction   is either UP or DOWN
	 * @param targetFloor is an integer that between separate elevator cars
	 */
	public FloorEvent(Time time, int floor, Direction button, int targetFloor) {
		this.time = time;
		this.floor = floor;
		this.button = button;
		this.targetFloor = targetFloor;
	}

	/**
	 * parse method splits up the floor event data passed in
	 */
	private void parse(String floorEvent) throws ParseException {
		String[] spaceSplit = floorEvent.split(" ");
		if (spaceSplit.length == 4) {
			time = new Time(spaceSplit[0]);
			button = Direction.parse(spaceSplit[2]);

			try {
				floor = Integer.parseInt(spaceSplit[1]);
				targetFloor = Integer.parseInt(spaceSplit[3]);
			} catch (NumberFormatException e) {
				throw new ParseException("Invalid floor event", 0);
			}

		} else {
			throw new ParseException("Invalid floor event", 0);
		}
	}

	/**
	 * Creates a new floor event within the elevator floor maximum and minimum
	 * values
	 */
	public void createNewEvent() {
		try {
			this.time = new Time(java.time.LocalTime.now().toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.floor = RANDOM.nextInt((FloorID.MAXFLOOR - FloorID.MINFLOOR) + 1) + FloorID.MINFLOOR;
		do {
			this.targetFloor = RANDOM.nextInt((FloorID.MAXFLOOR - FloorID.MINFLOOR) + 1) + FloorID.MINFLOOR;
		} while (targetFloor == floor);
		if (targetFloor > floor) {
			button = Direction.UP;
		} else {
			button = Direction.DOWN;
		}
		Parser.deparse(FilePath.EVENT_FILE, toString());
	}

	/**
	 * getter for the time
	 * 
	 * @return the time
	 */
	public Time getTime() {
		return time;
	}

	/**
	 * getter for the floor value
	 * 
	 * @return the floor as an integer
	 */
	public int getFloor() {
		return floor;
	}

	/**
	 * getter for the elevator button
	 * 
	 * @return the direction either UP or DOWN
	 */
	public Direction getElevatorButton() {
		return button;
	}

	public int getTargetFloor() {
		return targetFloor;
	}

	@Override
	public String toString() {
		return time.toString() + " " + floor + " " + button + " " + targetFloor;
	}
}
