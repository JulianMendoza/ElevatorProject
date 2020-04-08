package sysc3033.group9.elevatorproject.test;

import static org.junit.Assert.*;

import org.junit.Test;

import sysc3033.group9.elevatorproject.elevator.ElevatorButton;

public class ElevatorButtonTest {

	private ElevatorButton ElevatorButton=new ElevatorButton(4);
	@Test
	public void testGetTargetFloor() {
		assertEquals(4, ElevatorButton.getTargetFloor());
	}

}
