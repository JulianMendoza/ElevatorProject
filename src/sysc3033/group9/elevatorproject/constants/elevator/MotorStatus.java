package sysc3033.group9.elevatorproject.constants.elevator;

/**
 * 
 * @author Giuseppe Papalia
 *
 */
public enum MotorStatus {

	UP, DOWN, IDLE;
	
	public String toString() {
		switch(this) {
		case IDLE:
			return "Idle";
		case DOWN:
			return "Down";
		case UP:
			return "Up";
		default:
			return null;
		
		}
	}
	
	
	
}
