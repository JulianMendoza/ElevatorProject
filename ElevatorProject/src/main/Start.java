package main;

import subsystem.CommunicationPipe;
import subsystem.elevator.Elevator;
import subsystem.floor.Floor;
import subsystem.scheduler.Scheduler;
import util.RandomEventGenerator;

/**
 * TODO Create the system
 * 
 *
 */
public class Start {
	public static void main(String[] args) {
		//RandomEventGenerator rng=new RandomEventGenerator(10,500,1500);
		//rng.generateEvent();
		CommunicationPipe pipe=new CommunicationPipe();
		Thread floorSubSystem = new Thread(new Floor(pipe),"FloorSubSystem");
		Thread elevatorSubSystem = new Thread(new Elevator(pipe),"ElevatorSubSystem");
		Thread schedulerSubSystem = new Thread(new Scheduler(pipe),"SchedulerSubSystem");
		floorSubSystem.start();
		elevatorSubSystem.start();
		schedulerSubSystem.start();
	}
}
