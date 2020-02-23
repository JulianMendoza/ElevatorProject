package sysc3033.group9.elevatorproject.system;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import sysc3033.group9.elevatorproject.constants.elevator.MotorStatus;
import sysc3033.group9.elevatorproject.elevator.Elevator;
import sysc3033.group9.elevatorproject.event.ElevatorEvent;
import sysc3033.group9.elevatorproject.event.FloorEvent;

public class ElevatorEventQueue {

	Map<Integer, Elevator> elevators;
	Map<Elevator, List<ElevatorEvent>> eventMap;;

	public ElevatorEventQueue(Map<Integer, Elevator> elevators) {
		this.elevators = elevators;
		eventMap = new HashMap<Elevator, List<ElevatorEvent>>();
		for (Elevator e : elevators.values()) {
			eventMap.put(e, new LinkedList<ElevatorEvent>());
		}
	}

	public void add(FloorEvent e) {

		// IF AN ELEVATOR IS ON THE SAME FLOOR
		for (Elevator elevator : elevators.values()) {
			if (elevator.getCurrentFloor() == e.getFloor()) {
				eventMap.get(elevator).add(new ElevatorEvent(elevator, e.getFloor()));
				sortQueue(elevator);
				break;
			}
		}

		// IF ELEVATOR WILL PASS SAID FLOOR EN ROUTE
		for (Elevator elevator : elevators.values()) {
			if (elevator.getMotor().getStatus().equals(MotorStatus.DOWN) && e.getFloor() < elevator.getCurrentFloor()) {
				// interrupt the elevator
				eventMap.get(elevator).add(new ElevatorEvent(elevator, e.getFloor()));
				sortQueue(elevator);
				break;
			}

			if (elevator.getMotor().getStatus().equals(MotorStatus.UP) && e.getFloor() > elevator.getCurrentFloor()) {
				// interrupt the elevator
				eventMap.get(elevator).add(new ElevatorEvent(elevator, e.getFloor()));
				sortQueue(elevator);
				break;
			}
		}

		// IF ELEVATOR IS IDLE
		for (Elevator elevator : elevators.values()) {
			if (elevator.getMotor().getStatus().equals(MotorStatus.IDLE)) {
				eventMap.get(elevator).add(new ElevatorEvent(elevator, e.getFloor()));
				sortQueue(elevator);
				break;
			}

		}

	}

	public void sortQueue(Elevator e) {

	}

}
