package sysc3033.group9.elevatorproject.util;

public class Sleeper {

	public static void sleep(long sleepTime) {
		try {
			//System.out.println("Waiting "+sleepTime+" milliseconds.");
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}