package sysc3033.group9.elevatorproject.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import sysc3033.group9.elevatorproject.constants.elevator.MotorStatus;
import sysc3033.group9.elevatorproject.elevator.Elevator;
import sysc3033.group9.elevatorproject.event.FloorEvent;
import sysc3033.group9.elevatorproject.simulate.ElevatorSimulator;

/**
 * 
 * @author Giuseppe Papalia
 *
 */
public class FloorEventQueue {

	Map<Integer, Elevator> elevators;
	Map<Elevator, List<FloorEvent>> eventMap;;

	public FloorEventQueue(Map<Integer, Elevator> elevators) {
		this.elevators = elevators;
		eventMap = new HashMap<Elevator, List<FloorEvent>>();
		for (Elevator e : elevators.values()) {
			eventMap.put(e, new LinkedList<FloorEvent>());
		}
	}

	public synchronized List<FloorEvent> removePriorityEvents() {
		List<FloorEvent> priorityEvents = new ArrayList<FloorEvent>();
		for (Elevator elevator : eventMap.keySet()) {
			List<FloorEvent> eventQueue = eventMap.get(elevator);
			if (!eventQueue.isEmpty() || !elevator.isBusy()) {
				priorityEvents.add(eventQueue.remove(0));
			}
		}
		return priorityEvents;
	}

	public synchronized void add(FloorEvent e) {
		Elevator elevator = elevators.get(e.getElevatorCarID());
		List<FloorEvent> eventQueue = eventMap.get(elevator);
		eventQueue.add(e);
		sortQueue(elevator, eventQueue);
	}

	private void sortQueue(Elevator elevator, List<FloorEvent> eventQueue) {
		MotorStatus status = elevator.getMotor().getStatus();
		int currentFloor = elevator.getCurrentFloor();
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
