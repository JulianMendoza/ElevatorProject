package sysc3033.group9.elevatorproject.floor;

import java.util.ArrayList;
import java.util.List;

import sysc3033.group9.elevatorproject.constants.Direction;

public class Floor {

	private int floorID;
	private List<FloorButton> floorButtons;

	public Floor(int floorID, boolean isMinFloor, boolean isMaxFloor) {
		this.floorID = floorID;
		createFloor(isMinFloor, isMaxFloor);
	}

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

	public int getFloorID() {
		return floorID;
	}

}
