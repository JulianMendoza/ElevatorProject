package sysc3033.group9.elevatorproject.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import sysc3033.group9.elevatorproject.constants.Direction;
import sysc3033.group9.elevatorproject.event.FloorEvent;
import sysc3033.group9.elevatorproject.util.Parser;
import sysc3033.group9.elevatorproject.util.Time;

public class ParserTest {
	private File file;
	private String cwd, event;
	private FloorEvent[] floorEvent;

	@Before
	public void initialize() {
		cwd = new File("").getAbsolutePath();
		cwd += "/testfolder/test.txt";
		event = "14:05:15.0 2 Up 4";
		file = new File(cwd);
	}

	@Test
	public void pathExists() {
		assertTrue(file.exists());
	}

	@Test
	public void parseAndDeparseTest() {
		Parser.deparse(cwd, event);
		floorEvent = Parser.readTextFile(cwd, file);
		try {
			assertEquals(floorEvent[0].getTime(), new Time("14:05:15.0"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		assertEquals(floorEvent[0].getFloor(), 2);
		assertEquals(floorEvent[0].getElevatorButton(), Direction.UP);
		assertEquals(floorEvent[0].getTargetFloor(), 4);
	}

}
