package util;

import java.util.Random;

import constants.ElevatorButton;
import constants.FloorID;

public class RandomFloorEvent {
	private String time;
	private int floor;
	private ElevatorButton button;
	private int carID;
	private static final Random RANDOM = new Random();

	public RandomFloorEvent() {

		time = java.time.LocalTime.now().toString();

		floor = RANDOM.nextInt((FloorID.MAXFLOOR - FloorID.MINFLOOR) + 1) + FloorID.MINFLOOR;
		do {
			carID = RANDOM.nextInt((FloorID.MAXFLOOR - FloorID.MINFLOOR) + 1) + FloorID.MINFLOOR;
		} while (carID == floor);
		if(carID>floor) {
			button=ElevatorButton.UP;
		}else {
			button=ElevatorButton.DOWN;
		}
	}

	public String getTime() {
		return time;
	}

	public int getFloor() {
		return floor;
	}

	public ElevatorButton getElevatorButton() {
		return button;
	}

	public int getElevatorCarID() {
		return carID;
	}
}