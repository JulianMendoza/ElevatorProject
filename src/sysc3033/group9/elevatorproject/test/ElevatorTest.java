package sysc3033.group9.elevatorproject.test;

import static org.junit.Assert.*;

import org.junit.Test;
import sysc3033.group9.elevatorproject.elevator.Elevator;
import sysc3033.group9.elevatorproject.floor.FloorSpan;


/**
 * Tests for Elevator class
 * @author Kelly Harrison
 *
 */
public class ElevatorTest {
	
	@Test
	public void testgetDoor() {
		FloorSpan fs = new FloorSpan(1,5);
		Elevator e = new Elevator(fs);
		
		assertEquals(e.getDoor().toString(), "Door is closed");
	}
	
	@Test
	public void testgetMotor() {
		FloorSpan fs = new FloorSpan(1,5);
		Elevator e = new Elevator(fs);
		
		assertEquals(e.getMotor().toString(), "Motor is idle");
	}
	
	@Test
	public void testgetCurrentFloor() {
		FloorSpan fs = new FloorSpan(1,5);
		Elevator e = new Elevator(fs);
		
		assertEquals(e.getCurrentFloor(), 1);
	}
	
	@Test
	public void testisLoaded() {
		FloorSpan fs = new FloorSpan(1,5);
		Elevator e = new Elevator(fs);
		e.setLoaded(true);
		
		assertEquals(e.isLoaded(), true);
	}
	
	@Test 
	public void testisBusy() {
		FloorSpan fs = new FloorSpan(1,5);
		Elevator e = new Elevator(fs);
		e.setBusy(true);
		
		assertEquals(e.isBusy(), true);
	}
}
