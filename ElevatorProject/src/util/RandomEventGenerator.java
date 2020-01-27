package util;

import java.util.Random;

public class RandomEventGenerator {
	private int numEvents;
	private int maxWaitTime;
	private int minWaitTime;
	private RandomFloorEvent randomEvent;
	private static final Random RANDOM = new Random();
	
	public RandomEventGenerator(int numEvents) {
		this.numEvents=numEvents;
	}
	public void generateEvent() {
		for(int i=0;i<numEvents;i++) {
			randomEvent=new RandomFloorEvent();
			//do something with the generated event
			Sleeper.sleep(RANDOM.nextInt((maxWaitTime-minWaitTime)+1)+minWaitTime);
		}
	}
}
