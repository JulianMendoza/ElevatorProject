package test;

import static org.junit.Assert.*;
import java.io.File;
import java.util.List;
import org.junit.Test;
import util.Parser;

public class ParserTest {
	
	@Test
	public void parseElevatorDatatest() {
		String cwd=new File("").getAbsolutePath();
		cwd+="/testfolder/test.txt";
		List<String> testingList=Parser.readTextFile(cwd);
			assertTrue(testingList.contains("14:05:15.0"));
			assertTrue(testingList.contains("2"));
			assertTrue(testingList.contains("Up"));
			assertTrue(testingList.contains("4"));
	}

}
