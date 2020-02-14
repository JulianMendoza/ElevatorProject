package sysc3033.group9.elevatorproject.system;

import java.util.ArrayList;
import java.util.List;

import sysc3033.group9.elevatorproject.event.FloorEvent;

/**
 * Gateway for all threads to communicate Contains the necessary information for processing signals
 * 
 * @author Julian Mendoza, Giuseppe Papalia
 *
 */
public class Schedule {

	private List<FloorEvent> toSchedule;

	public Schedule() {
		toSchedule = new ArrayList<FloorEvent>();
	}

	public synchronized boolean hasEvents() {
		return !toSchedule.isEmpty();
	}

	public synchronized void schedule(FloorEvent floorEvent) {
		toSchedule.add(floorEvent);
	}

	public synchronized FloorEvent getEvent() {
		return toSchedule.remove(0);
	}

}
