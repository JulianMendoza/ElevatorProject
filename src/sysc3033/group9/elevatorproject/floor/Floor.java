package sysc3033.group9.elevatorproject.floor;

import java.util.ArrayList;
import java.util.List;

import sysc3033.group9.elevatorproject.constants.Direction;

/**
 * Floor class represents the different floors of the elevator
 * 
 * @author Giuseppe Papalia
 * @documentation Kelly Harrison
 * 
 */
public class Floor {

	private int floorID;
	private List<FloorButton> floorButtons;

	/**
	 * Constructor for Floor class
	 * 
	 * @param floorID    is the integer of what floor the elevator is at
	 * @param isMinFloor is true if the elevator is on the minimum floor, false otherwise
	 * @param isMaxFloor is true if the elevator is on the maximum floor, false otherwise
	 */
	public Floor(int floorID, boolean isMinFloor, boolean isMaxFloor) {
		this.floorID = floorID;
		createFloor(isMinFloor, isMaxFloor);
	}

	/**
	 * Creates a floor for the elevator to be on
	 * 
	 * @param isMinFloor is true if the elevator is on the minimum floor, false otherwise
	 * @param isMaxFloor is true if the elevator is on the maximum floor, false otherwise
	 */
	private void createFloor(boolean isMinFloor, boolean isMaxFloor) {
		floorButtons = new ArrayList<FloorButton>();

		if (isMinFloor) {
			floorButtons.add(new FloorButton(Direction.UP));

		} else if (isMaxFloor) {
			floorButtons.add(new FloorButton(Direction.DOWN));

		} else {
			floorButtons.add(new FloorButton(Direction.UP));
			floorButtons.add(new FloorButton(Direction.DOWN));
		}
	}

	/**
	 * getter for the floor ID
	 * 
	 * @return an integer that represents the floor ID
	 */
	public int getFloorID() {
		return floorID;
	}

}
