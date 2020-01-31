package sysc3033.group9.elevatorproject.system;

import java.util.Map;

import sysc3033.group9.elevatorproject.constants.Direction;
import sysc3033.group9.elevatorproject.constants.SleepTime;
import sysc3033.group9.elevatorproject.constants.elevator.MotorStatus;
import sysc3033.group9.elevatorproject.elevator.Elevator;
import sysc3033.group9.elevatorproject.elevator.Motor;
import sysc3033.group9.elevatorproject.floor.FloorSpan;
import sysc3033.group9.elevatorproject.util.Sleeper;

public class ElevatorSystem implements Runnable {
	private CommunicationPipe pipe;
	private boolean isMoving;
	private int currentFloor;
	private Elevator elevator;

	public ElevatorSystem(FloorSpan floorSpan, CommunicationPipe pipe) {
		this.elevator = new Elevator(floorSpan);
		this.currentFloor = floorSpan.getMinFloorID();
		this.pipe = pipe;
		this.isMoving = false;
	}

	@Override
	public void run() {
		while (true) {
			if (pipe.isSchedulerToElevator()) {
				handleElevatorEvent();
			} else if (isMoving) {
				handleMove();
			}
			Sleeper.sleep(SleepTime.DEFAULT);
		}
	}

	private void handleElevatorEvent() {
		/**
		 * TODO if the elevator is not moving, update the motor to go in the
		 * corresponding direction otherwise process the stop generated by the scheduler
		 */
		MotorStatus status = elevator.getMotor().getStatus();
		// int floorToMove=pipe.getFloor
		if (status == MotorStatus.IDLE) {
			// if(currentFloor<)
			System.out.println(Thread.currentThread().getName() + " has received the signal and is currently moving.");
			pipe.setSchedulerToElevator(false);
			isMoving = true;
		}
	}

	private void handleMove() {
		/*
		 * TODO Once the elevator starts moving, it must update the lamps to the
		 * scheduler which will then notify the the floor subsystem
		 */
		Motor elevatorMotor = elevator.getMotor();
		Map.Entry<Integer, Integer> moveEvent = pipe.getNextEvent();
		if ((moveEvent.getKey() - currentFloor) != 0) {
			if ((moveEvent.getKey() - currentFloor) > 0) {
				elevatorMotor.setStatus(MotorStatus.UP);
				move(Direction.UP, moveEvent.getKey() - currentFloor);
			} else {
				elevatorMotor.setStatus(MotorStatus.DOWN);
				move(Direction.DOWN, currentFloor - moveEvent.getKey());
			}
		}
		if ((moveEvent.getValue() - currentFloor) > 0) {
			elevatorMotor.setStatus(MotorStatus.UP);
			move(Direction.UP, moveEvent.getValue() - currentFloor);
		} else {
			elevatorMotor.setStatus(MotorStatus.DOWN);
			move(Direction.DOWN, currentFloor - moveEvent.getValue());
		}
		isMoving = false;
		elevatorMotor.setStatus(MotorStatus.IDLE);
	}

	private void move(Direction direction, int steps) {
		for (int i = 0; i < steps; i++) {
			Sleeper.sleep(SleepTime.FLOOR);

			String str = "";
			if (direction == Direction.UP) {
				currentFloor++;
				str += "The Elevator has moved up to floor " + currentFloor;
			} else {
				currentFloor--;
				str += "The Elevator has moved down to floor " + currentFloor;
			}
			announce(str);
		}
		System.out.println("The Elevator has reached the Floor!");
		System.out.println("Opening the door...");
		Sleeper.sleep(SleepTime.LOAD);
		System.out.println("Closing the door...");
		Sleeper.sleep(SleepTime.LOAD);
	}

	private void announce(String msg) {
		System.out.println(msg);
		System.out.println(Thread.currentThread().getName() + " has signaled the lamps to the Scheduler.");
		pipe.elevatorToFloor();
	}

}
