package subsystem.floor;

import subsystem.CommunicationPipe;
import util.RandomFloorEvent;

public class Floor implements Runnable {
	private CommunicationPipe pipe;
	public Floor(CommunicationPipe pipe) {
		this.pipe=pipe;
	}

	@Override
	public void run() {
		/**
		while (true) {
			if(pipe.isPromptFloor()) {
				handleSchedulerEvent();
			}else {
			listen();
			}
			Sleeper.sleep(SleepTime.DEFAULT);
		}
		**/
		signal();
	}
	private void handleSchedulerEvent() {
		//do something
	}
	private void listen() {
		//if event occurs signal
		signal();
	}

	private void signal() {
		System.out.println(Thread.currentThread().getName()+" has recieved an event. Signaling to Scheduler");
		
		pipe.processFloorEvent(new RandomFloorEvent());
	}

}
