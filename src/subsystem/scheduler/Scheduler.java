package subsystem.scheduler;

import subsystem.CommunicationPipe;
import subsystem.floor.FloorEvent;
import util.Sleeper;

public class Scheduler implements Runnable {
	private CommunicationPipe pipe;
	public Scheduler(CommunicationPipe pipe) {
		this.pipe=pipe;
	}
	public void handleFloorEvent(FloorEvent e) {
		/*
		 * TODO process the event, create a queue to let the elevator know where to go next
		 * logical flow: is elevator moving in the direction of the floor, is there currently a queue etc
		 */
		System.out.println(Thread.currentThread().getName()+" has received the signal and is now notifying the elevator");
		pipe.sendToElevator();
		//do something
	}
	public void handleElevatorEvent() {
		System.out.println(Thread.currentThread().getName()+" has received elevators notifcation and is now notifying the floor");
		pipe.setElevatorEventNotifcation(false);
		pipe.schedulerToFloor();
	}
	@Override
	public void run() {
		while(true) {
			if(pipe.isFloorEventNotifcation()) {
				handleFloorEvent(pipe.getFloorEvent());
			}else if(pipe.isElevatorEventNotification()) {
				handleElevatorEvent();	
			}
			Sleeper.sleep(500);
		}
	}

}
