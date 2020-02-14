package sysc3033.group9.elevatorproject.main;

import java.text.ParseException;

import sysc3033.group9.elevatorproject.event.EventFile;
import sysc3033.group9.elevatorproject.event.FloorEvent;
import sysc3033.group9.elevatorproject.floor.FloorSpan;
import sysc3033.group9.elevatorproject.system.ElevatorSystem;
import sysc3033.group9.elevatorproject.system.FloorSystem;
import sysc3033.group9.elevatorproject.system.Schedule;
import sysc3033.group9.elevatorproject.system.Scheduler;

/**
 * Main wrapper class System begins here Generates one random event for now
 * 
 * @author Julian Mendoza
 * 
 */
public class Start {
	public static void main(String[] args) {
		Schedule schedule = new Schedule();
		EventFile eventFile = new EventFile();
		FloorSpan floorSpan = new FloorSpan(1, 7);
		ElevatorSystem elevatorSystem = new ElevatorSystem(floorSpan, 1);
		Thread floorSubSystem = new Thread(new FloorSystem(floorSpan, schedule, eventFile), "FloorSubSystem");
		Thread elevatorSubSystem = new Thread(elevatorSystem, "ElevatorSubSystem");
		Thread schedulerSubSystem = new Thread(new Scheduler(schedule, elevatorSystem), "SchedulerSubSystem");
		floorSubSystem.start();
		elevatorSubSystem.start();
		schedulerSubSystem.start();

		try {
			schedule.add(new FloorEvent("00:33:36.579 6 Down 0"));
			schedule.add(new FloorEvent("00:33:36.579 3 Down 0"));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
