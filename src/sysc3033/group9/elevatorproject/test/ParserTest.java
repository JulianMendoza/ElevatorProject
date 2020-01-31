package sysc3033.group9.elevatorproject.test;

import java.io.File;

import org.junit.Test;

public class ParserTest {
	@SuppressWarnings("unused")
	@Test
	public void parseElevatorDatatest() {
		String cwd = new File("").getAbsolutePath();
		cwd += "/testfolder/test.txt";
		// FloorEvent testEvent=Parser.readTextFile(cwd);
		// assertEquals(testEvent.getTime().getHours(),14);

	}

}
