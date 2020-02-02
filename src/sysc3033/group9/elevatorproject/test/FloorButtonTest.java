package sysc3033.group9.elevatorproject.test;

import static org.junit.Assert.*;

import org.junit.Test;

import sysc3033.group9.elevatorproject.constants.Direction;
import sysc3033.group9.elevatorproject.floor.FloorButton;

/**
 * @author Oluwafunbi Aboderin
 *
 * 101019806
 */
public class FloorButtonTest {

	private FloorButton FloorButton=new FloorButton(Direction.UP);
	@Test
	public void testGetTargetDirection() {
		assertEquals(Direction.UP, FloorButton.getTargetDirection());
		assertNotEquals(Direction.DOWN, FloorButton.getTargetDirection());
	}

}
