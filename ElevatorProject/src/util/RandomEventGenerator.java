package util;

import java.util.Random;

public class RandomEventGenerator {
	private int numEvents;
	private int maxWaitTime;
	private int minWaitTime;
	private RandomFloorEvent randomEvent;
	private static final Random RANDOM = new Random();
	
	public RandomEventGenerator(int numEvents,int min,int max) {
		this.numEvents=numEvents;
		this.minWaitTime=min;
		this.maxWaitTime=max;
	}
	public void generateEvent() {
		for(int i=0;i<numEvents;i++) {
			randomEvent=new RandomFloorEvent();
			Parser.deparse("event"+i+".txt",randomEvent.toString());
			Sleeper.sleep(RANDOM.nextInt((maxWaitTime-minWaitTime)+1)+minWaitTime);
		}
	}
}
