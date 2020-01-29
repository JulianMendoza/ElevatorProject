package sysc3033.group9.elevatorproject.elevator;

import sysc3033.group9.elevatorproject.constants.elevator.DoorStatus;

public class Door {

	private DoorStatus status;
	
	public Door(DoorStatus initialStatus) {
		status = initialStatus;
	}
	
	public void switchStatus() {
		status = status.switchStatus();
	}
	
	public DoorStatus getStatus() {
		return status;
	}
	
	public String toString() {
		return "Door is " + status.toString().toLowerCase();
	}
}
