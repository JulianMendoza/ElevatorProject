package sysc3033.group9.elevatorproject.elevator;

import sysc3033.group9.elevatorproject.constants.elevator.MotorStatus;

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
