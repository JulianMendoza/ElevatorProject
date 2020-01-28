package subsystem.elevator;

import subsystem.CommunicationPipe;
import util.Sleeper;

public class Elevator implements Runnable{
	private CommunicationPipe pipe;
	private boolean isMoving;
	public Elevator(CommunicationPipe pipe) {
		this.pipe=pipe;
		this.isMoving=false;
	}
	@Override
	public void run() {
		while(true) {
		if(pipe.isPromptElevator()) {
			handleElevatorEvent();
		}else if(isMoving) {
			handleMove();
		}
		Sleeper.sleep(500);
		}
	}
	private void handleElevatorEvent() {
		System.out.println(Thread.currentThread().getName()+" has received the signal and is currently moving.");
		pipe.setElevatorPrompt(false);
		isMoving=true;
	}
	private void handleMove() {
		//loop & decrement a value
		System.out.println(Thread.currentThread().getName()+" has signaled the lamps to the Scheduler.");
		pipe.elevatorToFloor();
		isMoving=false;
	}

}
