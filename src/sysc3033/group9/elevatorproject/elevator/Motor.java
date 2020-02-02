package sysc3033.group9.elevatorproject.elevator;

import sysc3033.group9.elevatorproject.constants.elevator.MotorStatus;

/**
 * Motor class which is part of the elevator
 * 
 * @author Giuseppe Papalia
 *
 */
public class Motor {

	private MotorStatus status;

	/**
	 * Default constructor
	 * 
	 * @param initialStatus
	 */
	public Motor(MotorStatus initialStatus) {
		status = initialStatus;
	}

	/**
	 * Method to set the status
	 * 
	 * @param status the status of the motor
	 */
	public void setStatus(MotorStatus status) {
		this.status = status;
	}

	/**
	 * Method to get the Status
	 * 
	 * @return MotorStatus the status of the motor
	 */
	public MotorStatus getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Motor is " + status.toString().toLowerCase();
	}

}
