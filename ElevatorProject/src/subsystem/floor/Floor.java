package subsystem.floor;

import constants.SleepTime;
import subsystem.CommunicationPipe;
import util.Sleeper;

public class Floor implements Runnable {
	private CommunicationPipe pipe;
	public Floor(CommunicationPipe pipe) {
		this.pipe=pipe;
	}

	@Override
	public void run() {
		while (true) {
			if(pipe.isPromptFloor()) {
				handleSchedulerEvent();
			}
			listen();
			Sleeper.sleep(SleepTime.DEFAULT);
		}
	}
	private void handleSchedulerEvent() {
		//do something
	}
	private void listen() {
		//if event occurs signal
		signal();
	}

	private void signal() {
		
	}

}
