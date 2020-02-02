package sysc3033.group9.elevatorproject.system;

import java.util.ArrayList;
import java.util.List;

import sysc3033.group9.elevatorproject.event.FloorEvent;

/**
 * Gateway for all threads to communicate Contains the necessary information for
 * processing signals
 * 
 * @author
 *
 */
public class CommunicationPipe {
	private boolean floorToScheduler, schedulerToFloor, elevatorToScheduler, schedulerToElevator;
	private FloorEvent generatedEvent;
	private List<int[]> nextInQueue;

	/**
	 * Default constructor
	 */
	public CommunicationPipe() {
		floorToScheduler = false;
		schedulerToFloor = false;
		elevatorToScheduler = false;
		schedulerToElevator = false;
		nextInQueue = new ArrayList<int[]>();
	}

	/**
	 * Communication from floor to scheduler Called by the floor when an event
	 * occurs
	 * 
	 * @param e The FloorEven that will be passed to the scheduler
	 */
	public synchronized void floorToScheduler(FloorEvent e) {
		generatedEvent = e;
		floorToScheduler = true;
		notifyAll();
	}

	/**
	 * Communication between scheduler to floor Called by the scheduler when lamps
	 * are updated
	 */
	public synchronized void schedulerToFloor() {
		schedulerToFloor = true;
		notifyAll();
	}

	/**
	 * Communication from scheduler to elevator Information passed by the elevator
	 * to the scheduler
	 * 
	 * @TODO implement semaphore so multiple events can be handled simultaneously
	 *       and not depend on the pipe
	 */
	public synchronized void sendToElevator(int[] map) {
		nextInQueue.add(map);
		floorToScheduler = false;
		while (schedulerToElevator) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		schedulerToElevator = true;
		notifyAll();
	}

	/**
	 * Communication from elevator to scheduler Called by the elevator to signal
	 * that floor has changed
	 */
	public synchronized void elevatorToScheduler() {
		elevatorToScheduler = true;
		notifyAll();
	}

	/*
	 * Getters and Setters for private variables
	 */
	public boolean isFloorToScheduler() {
		return floorToScheduler;
	}

	public boolean isSchedulerToFloor() {
		return schedulerToFloor;
	}

	public boolean isElevatorToScheduler() {
		return elevatorToScheduler;
	}

	public boolean isSchedulerToElevator() {
		return schedulerToElevator;
	}

	public FloorEvent getFloorEvent() {
		return generatedEvent;
	}

	public void setSchedulerToElevator(boolean event) {
		schedulerToElevator = event;
	}

	public void setElevatorToScheduler(boolean event) {
		elevatorToScheduler = event;
	}

	public void setSchedulerToFloor(boolean event) {
		schedulerToFloor = event;
	}

	public void setFloorToScheduler(boolean event) {
		floorToScheduler = event;
	}

	public int[] getNextInQueue() {
		return nextInQueue.remove(0);
	}
}
