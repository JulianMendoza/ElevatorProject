package util;

import java.text.ParseException;
import java.util.Random;

import constants.ElevatorButton;
import constants.FloorID;
import subsystem.floor.EventFile;
import subsystem.floor.FloorEvent;

public class RandomFloorEvent extends FloorEvent{
	private EventFile eventFile;
	private static final Random RANDOM = new Random();

	public RandomFloorEvent(EventFile eventFile) {
		this.eventFile=eventFile;
		createNewEvent();
	}
	public void createNewEvent() {
		try {
			this.time = new Time(java.time.LocalTime.now().toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.floor = RANDOM.nextInt((FloorID.MAXFLOOR - FloorID.MINFLOOR) + 1) + FloorID.MINFLOOR;
		do {
			this.carID = RANDOM.nextInt((FloorID.MAXFLOOR - FloorID.MINFLOOR) + 1) + FloorID.MINFLOOR;
		} while (carID == floor);
		if(carID>floor) {
			button=ElevatorButton.UP;
		}else {
			button=ElevatorButton.DOWN;
		}
		Parser.deparse(FloorID.EVENTFILE,super.toString());
	}
}