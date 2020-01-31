package sysc3033.group9.elevatorproject.util;

/**
 * 
 * @author Giuseppe Papalia
 *
 */
public class Button {

	private ButtonLamp lamp;

	public Button() {
		lamp = new ButtonLamp();
	}

	public ButtonLamp getLamp() {
		return lamp;
	}

}
