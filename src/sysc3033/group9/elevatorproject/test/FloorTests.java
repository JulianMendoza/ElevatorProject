package sysc3033.group9.elevatorproject.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;



import org.junit.Before;
import org.junit.Test;

import sysc3033.group9.elevatorproject.constants.Direction;
import sysc3033.group9.elevatorproject.floor.Floor;
import sysc3033.group9.elevatorproject.floor.FloorButton;
import sysc3033.group9.elevatorproject.floor.FloorSpan;

public class FloorTests {
	
	@Test
	public void getFloorIDTest() {
		Floor floor = new Floor(1,true, false);
		assertEquals(floor.getFloorID(), 1);
	}
	@Test
	public void getTargetDirection() {
		FloorButton floor = new FloorButton(Direction.UP);
		assertEquals(floor.getTargetDirection(), Direction.UP);
	}
	@Test
	public void FloorSpanTest() {
		FloorSpan floor = new FloorSpan(1, 5);
		assertEquals(floor.getMinFloorID(), 1);
		assertEquals(floor.getMaxFloorID(), 5);
	}
}
