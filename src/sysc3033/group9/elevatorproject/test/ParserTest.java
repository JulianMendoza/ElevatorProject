package sysc3033.group9.elevatorproject.test;

import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;

import sysc3033.group9.elevatorproject.event.FloorEvent;
import sysc3033.group9.elevatorproject.util.Parser;

public class ParserTest {
	
	@Test
	public void parseElevatorDatatest() {
		String cwd=new File("").getAbsolutePath();
		cwd+="/testfolder/test.txt";
		//FloorEvent testEvent=Parser.readTextFile(cwd);
			//assertEquals(testEvent.getTime().getHours(),14);
			
	}

}
