package sysc3033.group9.elevatorproject.floor;

import java.util.ArrayList;
import java.util.List;

import sysc3033.group9.elevatorproject.constants.floor.FloorButton;
import sysc3033.group9.elevatorproject.constants.floor.FloorLamp;

public class Floor {

	private int floorID;
	private List<FloorButton> floorButtons;
	private List<FloorLamp> floorLamps;

	public Floor(int floorID, boolean isMinFloor, boolean isMaxFloor) {
		this.floorID = floorID;
		createFloor(isMinFloor, isMaxFloor);
	}

	private void createFloor(boolean isMinFloor, boolean isMaxFloor) {
		floorButtons = new ArrayList<FloorButton>();
		floorLamps = new ArrayList<FloorLamp>();

		if (isMinFloor) {
			floorButtons.add(FloorButton.UP);
			floorLamps.add(FloorLamp.UP);
		} else if (isMaxFloor) {
			floorButtons.add(FloorButton.DOWN);
			floorLamps.add(FloorLamp.DOWN);
		} else {
			floorButtons.add(FloorButton.UP);
			floorButtons.add(FloorButton.DOWN);
			floorLamps.add(FloorLamp.UP);
			floorLamps.add(FloorLamp.DOWN);
		}
	}

	public int getFloorID() {
		return floorID;
	}

}
