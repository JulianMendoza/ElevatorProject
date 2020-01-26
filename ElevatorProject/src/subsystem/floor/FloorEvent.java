package subsystem.floor;

import java.text.ParseException;

import constants.ElevatorButton;
import util.Time;

public class FloorEvent {

	private Time time;
	private int floor;
	private ElevatorButton button;
	private int carID;

	public FloorEvent(String floorEvent) throws ParseException{
		parse(floorEvent);
	}
	
	public FloorEvent(Time time, int floor, ElevatorButton button, int carID) {
		this.time = time;
		this.floor = floor;
		this.button = button;
		this.carID = carID;
	}
	
	private void parse(String floorEvent) throws ParseException {
		String[] commaSplit = floorEvent.split(",");
		if(commaSplit.length == 4) {
			time = new Time(commaSplit[0]);
			button = ElevatorButton.parse(commaSplit[2]);
			
			try {
				floor = Integer.parseInt(commaSplit[1]);
				carID = Integer.parseInt(commaSplit[3]);
			} catch(NumberFormatException e) {
				throw new ParseException("Invalid floor event", 0);
			}
			
		} else {
			throw new ParseException("Invalid floor event", 0);
		}
	}
	
	public Time getTime() {
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
