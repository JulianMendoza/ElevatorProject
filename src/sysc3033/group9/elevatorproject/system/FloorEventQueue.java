package sysc3033.group9.elevatorproject.system;

import java.util.LinkedList;
import java.util.List;

import sysc3033.group9.elevatorproject.constants.elevator.MotorStatus;
import sysc3033.group9.elevatorproject.event.FloorEvent;

/**
 * A class to determine the logical queue of events to be executed based off of
 * the elevator simulator
 * 
 * @author Giuseppe Papalia
 *
 */
public class FloorEventQueue {

	private List<FloorEvent> eventQueue;
	private ElevatorSystem elevatorSystem;

	/**
	 * Default constructor
	 * 
	 * @param elevatorSystem The system linked to the eventQueue
	 */
	public FloorEventQueue(ElevatorSystem elevatorSystem) {
		this.elevatorSystem = elevatorSystem;
		eventQueue = new LinkedList<FloorEvent>();
	}

	/**
	 * Method to add a floor event to the queue
	 * 
	 * @param e The event to be added
	 */
	public synchronized void add(FloorEvent e) {
		eventQueue.add(e);
		sortQueue();
	}

	/**
	 * Method to peek at the queue
	 * 
	 * @return the first item in the queue
	 */
	public synchronized FloorEvent peek() {
		if (!eventQueue.isEmpty()) {
			return eventQueue.get(0);
		}
		return null;
	}

	/**
	 * Method to remove the first item in the queue
	 * 
	 * @return
	 */
	public synchronized FloorEvent remove() {
		if (!isEmpty()) {
			return eventQueue.remove(0);
		}
		return null;
	}

	/**
	 * Method to determine if the queue is empty
	 * 
	 * @return true if the queue is empty; false if it is not
	 */
	public boolean isEmpty() {
		return eventQueue.isEmpty();
	}

	/**
	 * Method to get the queue
	 * 
	 * @return the eventQueue
	 */
	public List<FloorEvent> getQueue() {
		return eventQueue;
	}

	/**
	 * Method to sort the queue, puts the queue in a logical order
	 */
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
