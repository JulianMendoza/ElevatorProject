package test;

import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;

import subsystem.floor.FloorEvent;
import util.Parser;

public class ParserTest {
	
	@Test
	public void parseElevatorDatatest() {
		String cwd=new File("").getAbsolutePath();
		cwd+="/testfolder/test.txt";
		//FloorEvent testEvent=Parser.readTextFile(cwd);
			//assertEquals(testEvent.getTime().getHours(),14);
			
	}

}
