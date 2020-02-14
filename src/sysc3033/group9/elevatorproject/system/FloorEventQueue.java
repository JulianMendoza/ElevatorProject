package sysc3033.group9.elevatorproject.system;

import java.util.LinkedList;
import java.util.List;

import sysc3033.group9.elevatorproject.constants.elevator.MotorStatus;
import sysc3033.group9.elevatorproject.event.FloorEvent;

/**
 * 
 * @author Giuseppe Papalia
 *
 */
public class FloorEventQueue {

	List<FloorEvent> eventQueue;
	private ElevatorSystem elevatorSystem;

	public FloorEventQueue(ElevatorSystem elevatorSystem) {
		this.elevatorSystem = elevatorSystem;
		eventQueue = new LinkedList<FloorEvent>();
	}

	public synchronized void add(FloorEvent e) {
		eventQueue.add(e);
		sortQueue();
	}

	public synchronized FloorEvent peek() {
		if (!eventQueue.isEmpty()) {
			return eventQueue.get(0);
		}
		return null;
	}

	public synchronized FloorEvent remove() {
		if (!isEmpty()) {
			return eventQueue.remove(0);
		}
		return null;
	}

	public boolean isEmpty() {
		return eventQueue.isEmpty();
	}

	public List<FloorEvent> getQueue() {
		return eventQueue;
	}

	private void sortQueue() {
		MotorStatus status = elevatorSystem.getElevator().getMotor().getStatus();
		int currentFloor = elevatorSystem.getCurrentFloor();
		List<FloorEvent> eventQueueCopy = new LinkedList<FloorEvent>(eventQueue);
		eventQueue.clear();
		ElevatorSimulator simulator = new ElevatorSimulator(status, currentFloor);
		while (!eventQueueCopy.isEmpty()) {
			FloorEvent nextLogicalEvent = simulator.simulateLogicalEvent(eventQueueCopy);
			eventQueue.add(nextLogicalEvent);
			eventQueueCopy.remove(nextLogicalEvent);
		}

	}

}
