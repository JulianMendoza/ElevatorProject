package sysc3033.group9.elevatorproject.util;

public class ButtonLamp {

	boolean isLit;

	public ButtonLamp() {
		isLit = false;
	}

	public void switchStatus() {
		isLit = !isLit;
	}

	public boolean isLit() {
		return isLit;
	}

}