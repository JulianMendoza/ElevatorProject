package subsystem.scheduler;

import subsystem.CommunicationPipe;

public class Scheduler implements Runnable {
	private CommunicationPipe pipe;
	public Scheduler(CommunicationPipe pipe) {
		this.pipe=pipe;
	}
	@Override
	public void run() {
		
	}

}
