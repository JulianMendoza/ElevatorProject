package sysc3033.group9.elevatorproject.main;

import java.util.Random;

import sysc3033.group9.elevatorproject.event.EventFile;
import sysc3033.group9.elevatorproject.event.FloorEvent;
import sysc3033.group9.elevatorproject.floor.FloorSpan;
import sysc3033.group9.elevatorproject.system.CommunicationPipe;
import sysc3033.group9.elevatorproject.system.ElevatorSystem;
import sysc3033.group9.elevatorproject.system.FloorSystem;
import sysc3033.group9.elevatorproject.system.Scheduler;
import sysc3033.group9.elevatorproject.util.Sleeper;

/**
 * 
 * @author Julian Mendoza
 *
 */
public class Start {
	public static void main(String[] args) {
		CommunicationPipe pipe = new CommunicationPipe();
		EventFile eventFile = new EventFile();
		FloorSpan floorSpan = new FloorSpan(1, 7);
		Thread floorSubSystem = new Thread(new FloorSystem(floorSpan, pipe, eventFile), "FloorSubSystem");
		Thread elevatorSubSystem = new Thread(new ElevatorSystem(floorSpan, pipe), "ElevatorSubSystem");
		Thread schedulerSubSystem = new Thread(new Scheduler(pipe), "SchedulerSubSystem");
		floorSubSystem.start();
		elevatorSubSystem.start();
		schedulerSubSystem.start();
//		new FloorEvent();
//		for (int i = 0; i < numEvents; i++) {
//			//generator.createNewEvent();
//			Sleeper.sleep(RANDOM.nextInt((maxWaitTime - minWaitTime) + 1) + minWaitTime);
//		}
		for (int i = 0; i < 5; i++) {
			new FloorEvent();
			Sleeper.sleep(new Random().nextInt((3000 - 666) + 1) + 666);
		}
	}
}
