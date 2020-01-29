package sysc3033.group9.elevatorproject.event;

import java.text.ParseException;
import java.util.Random;

import sysc3033.group9.elevatorproject.constants.Direction;
import sysc3033.group9.elevatorproject.constants.FilePath;
import sysc3033.group9.elevatorproject.constants.FloorID;
import sysc3033.group9.elevatorproject.util.Parser;
import sysc3033.group9.elevatorproject.util.Time;

public class FloorEvent {

	private Time time;
	private int floor;
	private Direction button;
	private int carID;
	private static final Random RANDOM = new Random();

	public FloorEvent() {
		createNewEvent();
	}

	public FloorEvent(String floorEvent) throws ParseException {
		parse(floorEvent);
	}

	public FloorEvent(Time time, int floor, Direction button, int carID) {
		this.time = time;
		this.floor = floor;
		this.button = button;
		this.carID = carID;
	}

	private void parse(String floorEvent) throws ParseException {
		String[] spaceSplit = floorEvent.split(" ");
		if (spaceSplit.length == 4) {
			time = new Time(spaceSplit[0]);
			button = Direction.parse(spaceSplit[2]);

			try {
				floor = Integer.parseInt(spaceSplit[1]);
				carID = Integer.parseInt(spaceSplit[3]);
			} catch (NumberFormatException e) {
				throw new ParseException("Invalid floor event", 0);
			}

		} else {
			throw new ParseException("Invalid floor event", 0);
		}
	}

	private void createNewEvent() {
		try {
			this.time = new Time(java.time.LocalTime.now().toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.floor = RANDOM.nextInt((FloorID.MAXFLOOR - FloorID.MINFLOOR) + 1) + FloorID.MINFLOOR;
		do {
			this.carID = RANDOM.nextInt((FloorID.MAXFLOOR - FloorID.MINFLOOR) + 1) + FloorID.MINFLOOR;
		} while (carID == floor);
		if (carID > floor) {
			button = Direction.UP;
		} else {
			button = Direction.DOWN;
		}
		Parser.deparse(FilePath.EVENT_FILE, toString());
	}

	public Time getTime() {
		return time;
	}

	public int getFloor() {
		return floor;
	}

	public Direction getElevatorButton() {
		return button;
	}

	public int getElevatorCarID() {
		return carID;
	}

	@Override
	public String toString() {
		return time.toString() + " " + floor + " " + button + " " + carID;
	}
}
