package sysc3033.group9.elevatorproject.main;

import java.util.Random;

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
import sysc3033.group9.elevatorproject.util.Sleeper;

/**
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
//		new FloorEvent();
//		for (int i = 0; i < numEvents; i++) {
//			//generator.createNewEvent();
//			Sleeper.sleep(RANDOM.nextInt((maxWaitTime - minWaitTime) + 1) + minWaitTime);
//		}
		for (int i = 0; i < 5; i++) {
			new FloorEvent();
			view.setText(view.getEventPanelText(), "EVENT" + (i + 1) + ":"
					+ Parser.readTextFile(FilePath.EVENT_FILE, eventFile.getFile()).toString() + "\n");
			Sleeper.sleep(new Random().nextInt((5000 - 200) + 1) + 200);
		}
	}
}
