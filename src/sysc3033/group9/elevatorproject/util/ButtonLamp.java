package sysc3033.group9.elevatorproject.util;

/**
 * 
 * @author Giuseppe Papalia
 *
 */
public class ButtonLamp {

	boolean isLit;

	public ButtonLamp() {
		isLit = false;
	}

	/**
	 * Change the lamp status to the opposite of the current status
	 */
	public void switchStatus() {
		isLit = !isLit;
	}

	/**
	 * Whether the lamp is currently lit
	 * 
	 * @return
	 */
	public boolean isLit() {
		return isLit;
	}

}
