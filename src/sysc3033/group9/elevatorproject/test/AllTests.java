package sysc3033.group9.elevatorproject.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ParserTest.class, TimeParserTest.class, DoorTest.class, ElevatorButtonTest.class, FloorButtonTest.class,
		MotorTest.class, FloorTests.class })
public class AllTests {

}
