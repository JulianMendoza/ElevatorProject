package subsystem.elevator;

import constants.elevator.MotorStatus;

public class Motor {

	private MotorStatus status;
	
	public Motor(MotorStatus initialStatus) {
		status = initialStatus;
	}
	
	public void setStatus(MotorStatus status) {
		this.status = status;
	}
	
	public MotorStatus getStatus() {
		return status;
	}
	
	public String toString() {
		return "Motor is " + status.toString().toLowerCase();
	}
	
}
