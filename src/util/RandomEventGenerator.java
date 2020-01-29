package util;

import java.util.Random;

import subsystem.floor.FloorEvent;

public class RandomEventGenerator {
	private int numEvents;
	private int maxWaitTime;
	private int minWaitTime;
	private FloorEvent generator;
	private static final Random RANDOM = new Random();
	
	public RandomEventGenerator(int numEvents,int min,int max,FloorEvent generator) {
		this.numEvents=numEvents;
		this.minWaitTime=min;
		this.maxWaitTime=max;
		this.generator=generator;
	}
	public void generateEvent() {
		for(int i=0;i<numEvents;i++) {
			generator.createNewEvent();
			Sleeper.sleep(RANDOM.nextInt((maxWaitTime-minWaitTime)+1)+minWaitTime);
		}
	}
}
