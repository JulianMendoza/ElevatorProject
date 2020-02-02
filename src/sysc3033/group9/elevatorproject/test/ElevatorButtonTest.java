package sysc3033.group9.elevatorproject.test;

import static org.junit.Assert.*;

import org.junit.Test;

import sysc3033.group9.elevatorproject.elevator.ElevatorButton;
/**
 * @author Oluwafunbi Aboderin
 *
 * 101019806
 */
public class ElevatorButtonTest {

	private ElevatorButton ElevatorButton=new ElevatorButton(4);
	@Test
	public void testGetTargetFloor() {
		assertEquals(4, ElevatorButton.getTargetFloor());
	}

}
