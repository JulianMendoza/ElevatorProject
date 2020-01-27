package test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import util.Time;

public class TimeParserTest {
	private Time time;
	@Before
	public void initialize() {
	try {
		time=new Time("14:05:15.0");
	} catch (ParseException e) {
		e.printStackTrace();
	}
	}
	@Test
	public void parseTest() {
		assertEquals(time.getHours(),14);
	}

}
