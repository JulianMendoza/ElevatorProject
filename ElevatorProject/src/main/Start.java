package main;

import util.RandomEventGenerator;

/**
 * TODO Create the system
 * 
 *
 */
public class Start {
	public static void main(String[] args) {
		RandomEventGenerator rng=new RandomEventGenerator(10,500,1500);
		rng.generateEvent();
	}
}
