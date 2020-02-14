package sysc3033.group9.elevatorproject.system;

/**
 * Scheduler Thread handles the communication between the elevator and floor subsystem
 * 
 * @author Julian Mendoza
 *
 */
public class Scheduler implements Runnable {
	private ElevatorSystem elevatorSystem;

	/**
	 * Default constructor of the Scheduler
	 * 
	 * @param pipe The communication pipe
	 * @param view GUI view
	 */
	public Scheduler(Schedule schedule, ElevatorSystem elevatorSystem) {
		this.elevatorSystem = elevatorSystem;
	}

	@Override
	public void run() {
		while (true) {

		}
	}

}
