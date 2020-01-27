package subsystem.elevator;

import subsystem.CommunicationPipe;

public class Elevator implements Runnable{
	private CommunicationPipe pipe;
	public Elevator(CommunicationPipe pipe) {
		this.pipe=pipe;
	}
	@Override
	public void run() {
		
		
	}

}
