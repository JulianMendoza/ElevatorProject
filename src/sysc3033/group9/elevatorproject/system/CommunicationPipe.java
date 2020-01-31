package sysc3033.group9.elevatorproject.system;

import java.util.Map;
import java.util.Map.Entry;

import sysc3033.group9.elevatorproject.event.FloorEvent;

/**
 * 
 * @author 
 *
 */
public class CommunicationPipe {
	private boolean floorToScheduler, schedulerToFloor, elevatorToFloor, schedulerToElevator;
	private FloorEvent event;
	private Map.Entry<Integer, Integer> floorMap;

	public CommunicationPipe() {
		floorToScheduler = false;
		schedulerToFloor = false;
		elevatorToFloor = false;
		schedulerToElevator = false;
	}

	/**
	 * Communication from floor to scheduler
	 * 
	 * @param e
	 */
	public synchronized void floorToScheduler(FloorEvent e) {
		event = e;
		floorToScheduler = true;
		notifyAll();
	}

	/**
	 * Communication between scheduler to floor
	 */
	public synchronized void schedulerToFloor() {
		schedulerToFloor = true;
		notifyAll();
	}

	/**
	 * Communication from scheduler to elevator
	 */
	public synchronized void sendToElevator(Entry<Integer, Integer> floorMap) {
		this.floorMap = floorMap;
		floorToScheduler = false;
		schedulerToElevator = true;
		notifyAll();
	}

	/**
	 * Communication from elevator to scheduler
	 */
	public synchronized void elevatorToFloor() {
		elevatorToFloor = true;
		notifyAll();
	}

	public boolean isFloorToScheduler() {
		return floorToScheduler;
	}

	public boolean isSchedulerToFloor() {
		return schedulerToFloor;
	}

	public boolean isElevatorToScheduler() {
		return elevatorToFloor;
	}

	public boolean isSchedulerToElevator() {
		return schedulerToElevator;
	}

	public FloorEvent getFloorEvent() {
		return event;
	}

	public void setSchedulerToElevator(boolean event) {
		schedulerToElevator = event;
	}

	public void setElevatorToFloor(boolean event) {
		elevatorToFloor = event;
	}

	public void setSchedulerToFloor(boolean event) {
		schedulerToFloor = event;
	}

	public void setFloorToScheduler(boolean event) {
		floorToScheduler = event;
	}

	public Map.Entry<Integer, Integer> getNextEvent() {
		return floorMap;
	}
}
