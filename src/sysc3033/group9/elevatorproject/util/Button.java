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

	/**
	 * Gets the lamp of the button
	 * 
	 * @return
	 */
	public ButtonLamp getLamp() {
		return lamp;
	}

}
