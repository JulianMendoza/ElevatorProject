package sysc3033.group9.elevatorproject.event;

import sysc3033.group9.elevatorproject.constants.SleepTime;
import sysc3033.group9.elevatorproject.constants.elevator.MotorStatus;
import sysc3033.group9.elevatorproject.elevator.Elevator;
import sysc3033.group9.elevatorproject.util.Sleeper;

/**
 * 
 * @author Giuseppe Papalia
 *
 */
public class ElevatorEvent implements Runnable {

	private int targetFloor;
	private Elevator elevator;
	private FloorEvent floorEvent;

	public ElevatorEvent(Elevator elevator, FloorEvent e) {
		this.targetFloor = e.getFloor();
		this.elevator = elevator;
		this.floorEvent = e;
	}

	public ElevatorEvent(Elevator elevator, int targetFloor) {
		this.elevator = elevator;
		this.targetFloor = targetFloor;
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		doElevatorEvent();

//		TimedCondition idle = new TimedCondition(SleepTime.IDLE_TIME) {
//			@Override
//			public boolean condition() {
//				return !elevator.isBusy();
//			}
//		};
//
//		if (idle.isConditionSatisfied()) {
//			elevator.getMotor().setStatus(MotorStatus.IDLE);
//		}
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

		if (floorEvent != null) {
			new ElevatorEvent(elevator, floorEvent.getTargetFloor());
		}
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
			System.out.println("Elevator is now at floor #" + elevator.getCurrentFloor());
		}
	}

	private void loadElevator() {
		// OPENING DOOR
		System.out.println("Elevator door is opening!");
		Sleeper.sleep(SleepTime.OPEN_DOOR);
		// DOOR OPEN
		System.out.println("Elevator door has opened!");
		elevator.getDoor().switchStatus();
		// LOAD AND CLOSE
		System.out.println("Elevator is being loaded!");
		Sleeper.sleep(SleepTime.LOAD_AND_CLOSE);
		System.out.println("Elevator is has closed!!");
		elevator.getDoor().switchStatus();
		// ELEVATOR LOADED
		System.out.println("Elevator has been loaded!!");
	}

}
