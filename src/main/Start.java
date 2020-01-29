package main;

import subsystem.CommunicationPipe;
import subsystem.elevator.Elevator;
import subsystem.floor.EventFile;
import subsystem.floor.FloorSystem;
import subsystem.floor.FloorEvent;
import subsystem.scheduler.Scheduler;
import util.RandomEventGenerator;

public class Start {
	public static void main(String[] args) {
		CommunicationPipe pipe=new CommunicationPipe();
		EventFile eventFile=new EventFile();
		FloorEvent rnf = new FloorEvent();
		Thread floorSubSystem = new Thread(new FloorSystem(pipe,eventFile),"FloorSubSystem");
		Thread elevatorSubSystem = new Thread(new Elevator(pipe),"ElevatorSubSystem");
		Thread schedulerSubSystem = new Thread(new Scheduler(pipe),"SchedulerSubSystem");
		floorSubSystem.start();
		elevatorSubSystem.start();
		schedulerSubSystem.start();
	}
}
