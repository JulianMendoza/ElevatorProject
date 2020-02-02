package sysc3033.group9.elevatorproject.util;

/**
 * Utility class for putting a Thread to sleep
 * 
 * @author giuse
 *
 */
public class Sleeper {

	/**
	 * Put the current Thread to sleep
	 * 
	 * @param sleepTime
	 */
	public static void sleep(long sleepTime) {
		try {
			// System.out.println("Waiting "+sleepTime+" milliseconds.");
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
