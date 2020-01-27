package subsystem.floor;

import constants.SleepTime;
import util.Sleeper;

public class Floor implements Runnable {
	
	public Floor() {
	
	}

	@Override
	public void run() {
		while (true) {
			listen();
			Sleeper.sleep(SleepTime.DEFAULT);
		}
	}

	private void listen() {
		signal();
		
	}

	private void signal() {
		
	}

}
