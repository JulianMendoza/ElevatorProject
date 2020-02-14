package sysc3033.group9.elevatorproject.event;

import sysc3033.group9.elevatorproject.constants.SleepTime;
import sysc3033.group9.elevatorproject.constants.elevator.MotorStatus;
import sysc3033.group9.elevatorproject.elevator.Elevator;
import sysc3033.group9.elevatorproject.util.Sleeper;
import sysc3033.group9.elevatorproject.util.TimedCondition;

/**
 * 
 * @author Giuseppe Papalia
 *
 */
public class ElevatorEvent implements Runnable {

	private int targetFloor;
	private Elevator elevator;

	public ElevatorEvent(Elevator elevator, int targetFloor) {
		this.targetFloor = targetFloor;
		this.elevator = elevator;
	}

	@Override
	public void run() {
		doElevatorEvent();

		TimedCondition idle = new TimedCondition(SleepTime.IDLE_TIME) {
			@Override
			public boolean condition() {
				return !elevator.isBusy();
			}
		};

		if (idle.isConditionSatisfied()) {
			elevator.getMotor().setStatus(MotorStatus.IDLE);
		}
	}

	private void doElevatorEvent() {
		elevator.setBusy(true);

		if (!elevator.isLoaded()) {
			loadElevator();
		}

		setMotorDirection();
		moveToFloor();
		loadElevator();

		elevator.setLoaded(true);
		elevator.setBusy(false);
	}

	private void setMotorDirection() {
		if (targetFloor > elevator.getCurrentFloor()) {
			elevator.getMotor().setStatus(MotorStatus.UP);
		} else if (targetFloor < elevator.getCurrentFloor()) {
			elevator.getMotor().setStatus(MotorStatus.DOWN);
		}
	}

	private void moveToFloor() {
		while (elevator.getCurrentFloor() != targetFloor) {
			Sleeper.sleep(SleepTime.FLOOR);
			elevator.incrementFloor();
			// ELEVATOR NOW AT X FLOOR
		}
	}

	private void loadElevator() {
		// OPENING DOOR
		Sleeper.sleep(SleepTime.OPEN_DOOR);
		// DOOR OPEN
		elevator.getDoor().switchStatus();
		// LOAD AND CLOSE
		Sleeper.sleep(SleepTime.LOAD_AND_CLOSE);
		elevator.getDoor().switchStatus();
		// ELEVATOR LOADED
	}

}
