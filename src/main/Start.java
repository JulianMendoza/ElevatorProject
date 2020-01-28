package main;

import subsystem.CommunicationPipe;
import subsystem.elevator.Elevator;
import subsystem.floor.EventFile;
import subsystem.floor.Floor;
import subsystem.scheduler.Scheduler;
import util.RandomEventGenerator;
import util.RandomFloorEvent;


public class Start {
	public static void main(String[] args) {
		CommunicationPipe pipe=new CommunicationPipe();
		EventFile eventFile=new EventFile();
		RandomFloorEvent rnf=new RandomFloorEvent();
		RandomEventGenerator rng=new RandomEventGenerator(1,500,1000,rnf);
		Thread floorSubSystem = new Thread(new Floor(pipe,eventFile),"FloorSubSystem");
		Thread elevatorSubSystem = new Thread(new Elevator(pipe),"ElevatorSubSystem");
		Thread schedulerSubSystem = new Thread(new Scheduler(pipe),"SchedulerSubSystem");
		floorSubSystem.start();
		elevatorSubSystem.start();
		schedulerSubSystem.start();
		rng.generateEvent();
	}
}
