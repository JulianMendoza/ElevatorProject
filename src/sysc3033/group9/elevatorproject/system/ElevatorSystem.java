package sysc3033.group9.elevatorproject.system;

import java.util.HashMap;
import java.util.Map;

import sysc3033.group9.elevatorproject.constants.SleepTime;
import sysc3033.group9.elevatorproject.elevator.Elevator;
import sysc3033.group9.elevatorproject.event.ElevatorEvent;
import sysc3033.group9.elevatorproject.event.FloorEvent;
import sysc3033.group9.elevatorproject.floor.FloorSpan;
import sysc3033.group9.elevatorproject.util.Sleeper;

/**
 * ElevatorSystem thread Handles events that the elevators must process
 * 
 * @author Julian Mendoza, Giuseppe Papalia
 *
 */
public class ElevatorSystem implements Runnable {

	Map<Integer, Elevator> elevators;
	FloorEventQueue eventQueue;

	/**
	 * Default constructor
	 * 
	 * @param floorSpan the span of the elevator
	 * @param pipe      the communication pipe
	 * @param view      GUI
	 */
	public ElevatorSystem(FloorSpan floorSpan, int numElevators) {
		elevators = new HashMap<Integer, Elevator>();
		for (int i = 0; i < numElevators; i++) {
			elevators.put(i, new Elevator(floorSpan));
		}
		eventQueue = new FloorEventQueue(elevators);
	}

	@Override
	public void run() {
		while (true) {
			for (FloorEvent e : eventQueue.removePriorityEvents()) {
				handleFloorEvent(e);
			}
			Sleeper.sleep(SleepTime.DEFAULT);
		}
	}

	public synchronized void scheduleEvent(FloorEvent e) {
		eventQueue.add(e);
	}

	private void handleFloorEvent(FloorEvent e) {
		ElevatorEvent e2 = createElevatorEvent(e);
		dispatchElevatorEvent(e2);
	}

	private ElevatorEvent createElevatorEvent(FloorEvent e) {
		return new ElevatorEvent(elevators.get(e.getElevatorCarID()), e.getFloor());
	}

	private void dispatchElevatorEvent(ElevatorEvent e) {
		Thread elevatorEvent = new Thread(e);
		elevatorEvent.start();
	}

}
