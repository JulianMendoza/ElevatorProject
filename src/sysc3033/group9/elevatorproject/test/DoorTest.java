package sysc3033.group9.elevatorproject.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import sysc3033.group9.elevatorproject.constants.elevator.DoorStatus;
import sysc3033.group9.elevatorproject.elevator.Door;

public class DoorTest {

	private Door door = new Door(DoorStatus.CLOSED);

	@Test
	public void testGetStatus() {
		assertEquals(DoorStatus.CLOSED, door.getStatus());
	}

	@Test
	public void testSwitchStatus() {
		door.switchStatus();
		assertEquals(DoorStatus.OPEN, door.getStatus());
	}
}
