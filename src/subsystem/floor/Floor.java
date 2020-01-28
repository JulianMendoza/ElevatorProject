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
		System.out.println("Floor has received the signal");
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
