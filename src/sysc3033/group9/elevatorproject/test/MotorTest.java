package sysc3033.group9.elevatorproject.test;

import static org.junit.Assert.*;

import org.junit.Test;

import sysc3033.group9.elevatorproject.constants.elevator.MotorStatus;
import sysc3033.group9.elevatorproject.elevator.Motor;

/**
 * @author Oluwafunbi Aboderin
 *
 * 101019806
 */
public class MotorTest {

	private Motor motor=new Motor(MotorStatus.UP);
	@Test
	public void testSetGetMotorStatus() {	
		motor.setStatus(MotorStatus.IDLE);
		assertEquals(MotorStatus.IDLE, motor.getStatus());
	}

}
