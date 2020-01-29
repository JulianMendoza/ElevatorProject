package sysc3033.group9.elevatorproject.elevator;

public class ElevatorLamp {

	private boolean isLit;

	public ElevatorLamp(boolean initialStatus) {
		isLit = initialStatus;
	}

	public void switchStatus() {
		isLit = !isLit;
	}

	public boolean isLit() {
		return isLit();
	}

}
