package sysc3033.group9.elevatorproject.system;

import java.util.LinkedList;
import java.util.List;

import sysc3033.group9.elevatorproject.constants.elevator.MotorStatus;
import sysc3033.group9.elevatorproject.event.FloorEvent;

/**
 * A class designed to simulate the logical moves of an elevator
 * 
 * @author Giuseppe Papalia
 *
 */
public class ElevatorSimulator {

	private MotorStatus motorStatus;
	private int currentFloor;

	public ElevatorSimulator(MotorStatus motorStatus, int currentFloor) {
		this.motorStatus = motorStatus;
		this.currentFloor = currentFloor;
	}

	/**
	 * Simulates and returns the next most logical event from a list of a events
	 * Uses the current floor and motor status to compute the next most logical
	 * event
	 * 
	 * @param events - A list of arbitrary floor events
	 * @return logicalEvent - The next most logical event
	 */
	public FloorEvent simulateLogicalEvent(List<FloorEvent> events) {
		FloorEvent logicalEvent = updateLogicalEvent(null, new LinkedList<FloorEvent>(events), false);
		doEvent(logicalEvent);
		return logicalEvent;
	}

	/**
	 * The motor status should already be correct so we simply need to update the
	 * floor
	 * 
	 * @see updateLogicalEvent - for motor status updates
	 * @param e - the event to be performed
	 */
	private void doEvent(FloorEvent e) {
		currentFloor = e.getFloor();
	}

	/**
	 * Recursive method to compute the most logical event
	 * 
	 * @param mostLogicalEvent - the current most logical event
	 * @param events           - an arbitrary list of events
	 * @param hasUpdated       - whether the most logical event has been updated
	 *                         beyond instantiation
	 * @return mostLogicalEvent - the most logical event after computation
	 */
	private FloorEvent updateLogicalEvent(FloorEvent mostLogicalEvent, List<FloorEvent> events, boolean hasUpdated) {
		boolean updatedEvent = false;

		if (mostLogicalEvent == null) { // assign an arbitrary most logical event
			mostLogicalEvent = events.get(0);
		}

		if (motorStatus.equals(MotorStatus.UP)) {
			for (FloorEvent e : events) { // check for events up along the way
				if (e.getFloor() > currentFloor && e.getFloor() < mostLogicalEvent.getFloor()) {
					mostLogicalEvent = e;
					updatedEvent = true;
					continue;
				}
			}
		} else if (motorStatus.equals(MotorStatus.DOWN)) { // check for events down along the way
			for (FloorEvent e : events) {
				if (e.getFloor() < currentFloor && e.getFloor() > mostLogicalEvent.getFloor()) {
					mostLogicalEvent = e;
					updatedEvent = true;
					continue;
				}
			}
		} else {
			for (FloorEvent e : events) { // check for closest event as it's idle
				if (Math.abs(currentFloor - e.getFloor()) < Math.abs(currentFloor - e.getFloor())) {
					mostLogicalEvent = e;
					updatedEvent = true;
					continue;
				}
			}
		}

		if (updatedEvent) { // we found a more logical events
			hasUpdated = true;
			events.remove(mostLogicalEvent);
			return updateLogicalEvent(mostLogicalEvent, events, hasUpdated);
		} else if (!hasUpdated) { // there are no events to do along the way so we can switch the direction of the
									// elevator and check again
			if (motorStatus.equals(MotorStatus.UP)) {
				motorStatus = MotorStatus.DOWN;
			} else if (motorStatus.equals(MotorStatus.DOWN)) {
				motorStatus = MotorStatus.UP;
			}
			return updateLogicalEvent(mostLogicalEvent, events, hasUpdated);
		} else { // we have computed the most logical event
			return mostLogicalEvent;
		}
	}

}
