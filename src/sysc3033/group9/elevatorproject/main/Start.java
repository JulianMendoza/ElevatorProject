package sysc3033.group9.elevatorproject.main;

import sysc3033.group9.elevatorproject.GUI.SystemView;
import sysc3033.group9.elevatorproject.constants.FilePath;
import sysc3033.group9.elevatorproject.event.EventFile;
import sysc3033.group9.elevatorproject.event.FloorEvent;
import sysc3033.group9.elevatorproject.floor.FloorSpan;
import sysc3033.group9.elevatorproject.system.CommunicationPipe;
import sysc3033.group9.elevatorproject.system.ElevatorSystem;
import sysc3033.group9.elevatorproject.system.FloorSystem;
import sysc3033.group9.elevatorproject.system.Scheduler;
import sysc3033.group9.elevatorproject.util.Parser;

/**
 * Main wrapper class System begins here Generates one random event for now
 * 
 * @author Julian Mendoza
 * 
 */
public class Start {
	public static void main(String[] args) {
		SystemView view = new SystemView();
		CommunicationPipe pipe = new CommunicationPipe();
		EventFile eventFile = new EventFile();
		FloorSpan floorSpan = new FloorSpan(1, 7);
		Thread floorSubSystem = new Thread(new FloorSystem(floorSpan, pipe, eventFile, view), "FloorSubSystem");
		Thread elevatorSubSystem = new Thread(new ElevatorSystem(floorSpan, pipe, view), "ElevatorSubSystem");
		Thread schedulerSubSystem = new Thread(new Scheduler(pipe, view), "SchedulerSubSystem");
		floorSubSystem.start();
		elevatorSubSystem.start();
		schedulerSubSystem.start();
		// for (int i = 0; i < 1; i++) {
		new FloorEvent();
		view.setText(view.getEventPanelText(),
				"EVENT" + 1 + ":" + Parser.readTextFile(FilePath.EVENT_FILE, eventFile.getFile()).toString() + "\n");
		// Sleeper.sleep(new Random().nextInt((5000 - 200) + 1) + 200);
		// }
	}
}
