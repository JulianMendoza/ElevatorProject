package sysc3033.group9.elevatorproject.system;

import sysc3033.group9.elevatorproject.constants.SleepTime;
import sysc3033.group9.elevatorproject.event.FloorEvent;
import sysc3033.group9.elevatorproject.util.Sleeper;

/**
 * Scheduler Thread handles the communication between the elevator and floor subsystem
 * 
 * @author Julian Mendoza, Giuseppe Papalia
 *
 */
public class Scheduler implements Runnable {
	private ElevatorSystem elevatorSystem;
	private Schedule schedule;

	/**
	 * Default constructor of the Scheduler
	 * 
	 * @param pipe The communication pipe
	 * @param view GUI view
	 */
	public Scheduler(Schedule schedule, ElevatorSystem elevatorSystem) {
		this.elevatorSystem = elevatorSystem;
		this.schedule = schedule;
	}

	@Override
	public void run() {
		while (true) {
			if (schedule.hasEvents()) {
				scheduleEvent(schedule.getEvent());
			}
			Sleeper.sleep(SleepTime.DEFAULT);
		}
	}

	public void scheduleEvent(FloorEvent e) {
		elevatorSystem.scheduleEvent(e, 0); // always use car 0 for now
	}
}
