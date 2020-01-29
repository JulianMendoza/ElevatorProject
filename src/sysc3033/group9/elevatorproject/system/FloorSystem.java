package sysc3033.group9.elevatorproject.system;

import sysc3033.group9.elevatorproject.constants.FilePath;
import sysc3033.group9.elevatorproject.constants.SleepTime;
import sysc3033.group9.elevatorproject.event.EventFile;
import sysc3033.group9.elevatorproject.util.Parser;
import sysc3033.group9.elevatorproject.util.Sleeper;

public class FloorSystem implements Runnable {
	private CommunicationPipe pipe;
	private EventFile eventFile;

	public FloorSystem(CommunicationPipe pipe, EventFile eventFile) {
		this.pipe = pipe;
		this.eventFile = eventFile;
	}

	private void handleSchedulerEvent() {
		/*
		 * TODO update the lamps on of the floor subsystem i.e, is the elevator going up or down, which floor is it on. If it is stationary, lamp is off
		 */
		System.out.println(Thread.currentThread().getName() + " has received the signal and has update the lamps");
		pipe.setFloorPrompt(false);
	}

	private void listen() {
		if (eventFile.isFileUpdated()) {
			signal();
		}
	}

	private void signal() {
		System.out.println(Thread.currentThread().getName() + " has recieved an event. Signaling to Scheduler");
		pipe.handleFloorEvent(Parser.readTextFile(FilePath.EVENT_FILE, eventFile.getFile()));
	}

	@Override
	public void run() {
		while (true) {
			if (pipe.isPromptFloor()) {
				handleSchedulerEvent();
			} else {
				listen();
			}
			Sleeper.sleep(SleepTime.DEFAULT);
		}

	}

}
