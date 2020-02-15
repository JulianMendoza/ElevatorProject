package sysc3033.group9.elevatorproject.system;

import java.util.ArrayList;
import java.util.List;

import sysc3033.group9.elevatorproject.GUI.SystemView;
import sysc3033.group9.elevatorproject.constants.FilePath;
import sysc3033.group9.elevatorproject.event.EventFile;
import sysc3033.group9.elevatorproject.event.FloorEvent;
import sysc3033.group9.elevatorproject.floor.Floor;
import sysc3033.group9.elevatorproject.floor.FloorSpan;
import sysc3033.group9.elevatorproject.util.Parser;

/**
 * FloorSubSystem class contains the knowledge of all floors. Button presses and
 * display lamps are updated here
 * 
 * @author Julian Mendoza, Giuseppe Papalia
 *
 */
public class FloorSystem implements Runnable {

	private List<Floor> floors;
	private Schedule pipe;
	private EventFile eventFile;
	private SystemView view;

	/**
	 * Constructor of the floor system
	 * 
	 * @param floorSpan The span of the number of floors
	 * @param pipe      A pipe which will communicate nessacary events
	 * @param eventFile System file which is polled to check if a new event has
	 *                  occured
	 * @param view      GUI
	 */
	public FloorSystem(FloorSpan floorSpan, Schedule pipe, EventFile eventFile, SystemView view) {
		createFloors(floorSpan);
		this.pipe = pipe;
		this.eventFile = eventFile;
		this.view = view;
	}

	/**
	 * Create the floors
	 * 
	 * @param floorSpan span of the number of floors
	 */
	private void createFloors(FloorSpan floorSpan) {
		floors = new ArrayList<Floor>();

		for (int i = floorSpan.getMinFloorID(); i <= floorSpan.getMaxFloorID(); i++) {
			floors.add(new Floor(i, i == floorSpan.getMinFloorID(), i == floorSpan.getMaxFloorID()));
		}
	}

	private void signal() {
		FloorEvent e = Parser.readTextFile(FilePath.EVENT_FILE, eventFile.getFile());
		// view.setText(view.getFloorText(), Thread.currentThread().getName() + " has
		// received an event.\nFloor #"
		// + e.getFloor() + " was pressed.\nSignaling to Scheduler\n");
		pipe.add(e);
	}

	@Override
	public void run() {
		while (true) {
			if (eventFile.isFileUpdated()) {
				signal();
			}
		}
	}
}