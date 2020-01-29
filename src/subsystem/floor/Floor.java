package subsystem.floor;

import constants.FloorID;
import constants.SleepTime;
import subsystem.CommunicationPipe;
import util.Parser;
import util.Sleeper;

public class Floor implements Runnable {
	private CommunicationPipe pipe;
	private EventFile eventFile;
	public Floor(CommunicationPipe pipe,EventFile eventFile) {
		this.pipe=pipe;
		this.eventFile= eventFile;
	}

	private void handleSchedulerEvent() {
		/*
		 * TODO update the lamps on of the floor subsystem i.e, is the elevator going up or down, which 
		 * floor is it on. If it is stationary, lamp is off
		 */
		System.out.println(Thread.currentThread().getName()+" has received the signal and has update the lamps");
		pipe.setFloorPrompt(false);
	}
	private void listen() {
		if(eventFile.isFileUpdated()) {
			signal();
		}
	}
	private void signal() {
		System.out.println(Thread.currentThread().getName()+" has recieved an event. Signaling to Scheduler");
		pipe.processFloorEvent(Parser.readTextFile(FloorID.EVENTFILE,eventFile.getFile()));
	}
	@Override
	public void run() {
		while (true) {
			if(pipe.isPromptFloor()) {
				handleSchedulerEvent();
			}else {
			listen();
			}
			Sleeper.sleep(SleepTime.DEFAULT);
		}
	
	}

}
