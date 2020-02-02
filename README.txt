                                   README.TXT
-----------------------------------------------------------------------------------

                  TEAM MEMBERS AND RESPONSIBILITIES:

Giuseppe Papalia - ElevatorSubSystem/FloorSubSystem/Design Framework/PackageLayouts
Julian Mendoza - SchedulerSubSystem/Concurrent Design/BugFix/Team Management
Oluwafunbi Aboderin - Sequence Diagram/JUnit tests/Version control/Code Review
Kelly Harrison - UML Diagram/JavaDocs of secondary Classes/JUnit tests/Code Review
Mohammad Gaffori - JUnit framework testsuites/Requirements/README/Code Review

-----------------------------------------------------------------------------------
INSTALLATION

SETUP INSTRUCTIONS:
a. Using Eclipse
	1. Use Eclipse to import a new Project File -> Import -> General -> Projects from Folder or Archive 
	2. Click Next
	2. Next to "Import source" click Directory... and locate the folder "A2G9_milestone1" 
	3. Select the "A2G9_project" folder
	4. Click finish
	5. Find Start.java via the path A2G9_project -> src -> sysc3033.group9.elevatorproject.main
	6. Right-click Start.java and run as a Java Application
b. Using Command Line (Using lab computers)
	1. Open command line
	2. Type in "java -jar " in the command line (but don't hit enter yet)
	3. Click and drag A2G9_project.jar into the command line
	4. Hit enter
NOTE you can double click the jar to run the GUI. No command line output will appear
-----------------------------------------------------------------------------------
FILES
-----------------------------------------------------------------------------------
-A2G_project
	-src/sysc3303.group9.elevatorproject.constants   !***Constants package***! 
		-.elevator
			-DoorStatus.java
			-MotorStatus.java
		-Direction.java
		-FilePath.java
		-FloorID.java
		-SleepTime.java

	-src/sysc3303.group9.elevatorproject.elevator     !***Elevator package***!
		-Door.java
		-Elevator.java
		-ElevatorButton.java
		-Motor.java

	-src/sysc3303.group9.elevatorproject.event        !***Event package***!
		-EventFile.java
		-FloorEvent.java

	-src/sysc3303.group9.elevatorproject.floor        !***Floor package***!
		-Floor.java
		-FloorButton.java
		-FloorSpan.java

	-src/sysc3303.group9.elevatorproject.GUI 	  !***GUI package***!
		-SystemView.java

	-src/sysc3303.group9.elevatorproject.main	  !***Main package***!
		-Start.java

	-src/sysc3303.group9.elevatorproject.system	  !***System package***!
		-CommunicationPipe.java
		-ElevatorSystem.java
		-FloorSystem.java
		-Scheduler.java	

	-src/sysc3303.group9.elevatorproject.test	  !***Test package***!
		-AllTests.java
		-DoorTest.java
		-ElevatorButtonTest.java
		-FloorButtonTest.java
		-FloorTests.java
		-MotorTest.java
		-ParserTest.java
		-TimeParserTest.java

	-src/sysc3303.group9.elevatorproject.util         !***Utility package***!
		-Button.java
		-ButtonLamp.java
		-Parser.java
		-Sleeper.java
		-Time.java
-data						     !***Data for iteration 0***!
	-iteration0data.xlsx
	-Iteration0explanation.docx
-diagrams						  !***Image folder***!
	-iter1Sequence.jpg
	-iter1UML.jpg
	-snapshotOfGUI.png  

-testfolder					!***EVENT FOLDER ***!
	-eventFile.txt
	-test.jpg

NOTE: GUI requires testfolder to be in the same directory as the java application