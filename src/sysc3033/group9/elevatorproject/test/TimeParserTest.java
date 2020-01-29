package sysc3033.group9.elevatorproject.test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import sysc3033.group9.elevatorproject.util.Time;

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
