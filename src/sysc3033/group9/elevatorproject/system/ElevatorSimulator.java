package sysc3033.group9.elevatorproject.system;

import java.util.List;

import sysc3033.group9.elevatorproject.constants.elevator.MotorStatus;
import sysc3033.group9.elevatorproject.event.FloorEvent;

public class ElevatorSimulator {

	private MotorStatus motorStatus;
	private int currentFloor;

	public ElevatorSimulator(MotorStatus motorStatus, int currentFloor) {
		this.motorStatus = motorStatus;
		this.currentFloor = currentFloor;
	}

	public FloorEvent simulateLogicalEvent(List<FloorEvent> events) {
		FloorEvent logicalEvent = getLogicalEvent(events.remove(0), events);
		doEvent(logicalEvent);
		return logicalEvent;
	}

	private void doEvent(FloorEvent e) {

	}

	private FloorEvent getLogicalEvent(FloorEvent logicalEvent, List<FloorEvent> events) {
		boolean updatedEvent = false;

		if (motorStatus.equals(MotorStatus.UP)) {
			for (FloorEvent e : events) { // check for stops up along the way
				if (e.getFloor() > currentFloor && e.getFloor() < logicalEvent.getFloor()) {
					logicalEvent = e;
					updatedEvent = true;
					continue;
				}
			}
		} else if (motorStatus.equals(MotorStatus.DOWN)) { // check for stops down along the way
			for (FloorEvent e : events) {
				if (e.getFloor() < currentFloor && e.getFloor() > logicalEvent.getFloor()) {
					logicalEvent = e;
					updatedEvent = true;
					continue;
				}
			}
		} else {
			for (FloorEvent e : events) { // check for closest stop as it's idle
				if (Math.abs(currentFloor - e.getFloor()) < Math.abs(currentFloor - e.getFloor())) {
					logicalEvent = e;
					updatedEvent = true;
					continue;
				}
			}
		}

		if (updatedEvent) {
			events.remove(logicalEvent);
			return getLogicalEvent(logicalEvent, events);
		} else if (logicalEvent == null) {
			if (motorStatus.equals(MotorStatus.UP)) {
				motorStatus = MotorStatus.DOWN;
			} else if (motorStatus.equals(MotorStatus.DOWN)) {
				motorStatus = MotorStatus.UP;
			}
			return getLogicalEvent(logicalEvent, events);
		} else {

			return logicalEvent;
		}
	}

}
