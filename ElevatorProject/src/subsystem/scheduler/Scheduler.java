package subsystem.scheduler;

import subsystem.CommunicationPipe;
import subsystem.floor.FloorEvent;

public class Scheduler implements Runnable {
	private CommunicationPipe pipe;
	public Scheduler(CommunicationPipe pipe) {
		this.pipe=pipe;
	}
	public void handleFloorEvent(FloorEvent e) {
		pipe.processFloorEvent(e);
		//do something
	}
	public void handleElevatorEvent() {
		pipe.processElevatorEvent();
	}
	@Override
	public void run() {
		while(true) {
			if(pipe.isFloorEventNotifcation()) {
				handleFloorEvent(pipe.getFloorEvent());
			}else if(pipe.isElevatorEventNotification()) {
				handleElevatorEvent();	
			}
		}
	}

}
