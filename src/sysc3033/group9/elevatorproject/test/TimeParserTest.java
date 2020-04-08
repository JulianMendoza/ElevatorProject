package sysc3033.group9.elevatorproject.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import sysc3033.group9.elevatorproject.util.Time;

public class TimeParserTest {
	private Time time, time2;
	private String str;

	@Before
	public void initialize() {
		str = "14:05:15.0";
		try {
			time = new Time("14:05:15.0");
			time2 = new Time(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void parseTest() {
		assertEquals(time.getHours(), 14);
		assertEquals(time.getMinutes(), 5);
		assertEquals(time.getSeconds(), 15);
		assertEquals(time.getMilliseconds(), 0);
	}

	@Test
	public void stringTest() {
		assertEquals(time.toString(), time2.toString());
	}

	@Test
	public void equalsTest() {
		assertTrue(time.equals(time2));
	}
}