package subsystem.floor;

import java.text.ParseException;

import constants.ElevatorButton;
import util.Time;

public class FloorEvent {

	protected Time time;
	protected int floor;
	protected ElevatorButton button;
	protected int carID;
	public FloorEvent() {
		
	}
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
		String[] spaceSplit = floorEvent.split(" ");
		if(spaceSplit.length == 4) {
			time = new Time(spaceSplit[0]);
			button = ElevatorButton.parse(spaceSplit[2]);
			
			try {
				floor = Integer.parseInt(spaceSplit[1]);
				carID = Integer.parseInt(spaceSplit[3]);
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
