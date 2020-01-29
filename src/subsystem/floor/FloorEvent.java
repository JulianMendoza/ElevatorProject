package subsystem.floor;

import java.text.ParseException;
import java.util.Random;

import constants.FloorID;
import constants.floor.FloorButton;
import util.Parser;
import util.Time;

public class FloorEvent {

	protected Time time;
	protected int floor;
	protected FloorButton button;
	protected int carID;
	private static final Random RANDOM = new Random();

	public FloorEvent() {
		createNewEvent();
	}

	public FloorEvent(String floorEvent) throws ParseException {
		parse(floorEvent);
	}

	public FloorEvent(Time time, int floor, FloorButton button, int carID) {
		this.time = time;
		this.floor = floor;
		this.button = button;
		this.carID = carID;
	}

	private void parse(String floorEvent) throws ParseException {
		String[] spaceSplit = floorEvent.split(" ");
		if (spaceSplit.length == 4) {
			time = new Time(spaceSplit[0]);
			button = FloorButton.parse(spaceSplit[2]);

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
			button = FloorButton.UP;
		} else {
			button = FloorButton.DOWN;
		}
		Parser.deparse(FloorID.EVENTFILE, toString());
	}

	public Time getTime() {
		return time;
	}

	public int getFloor() {
		return floor;
	}

	public FloorButton getElevatorButton() {
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
