package sysc3033.group9.elevatorproject.event;

import java.io.File;

import sysc3033.group9.elevatorproject.constants.FilePath;

/**
 * This file represents an elevator event; It simulates an elevator stop and go request
 * @author Julian Mendoza
 *
 */
public class EventFile {
	private long timeStamp;
	private File file;
	/**
	 * Constructor for EventFile class
	 */
	public EventFile() {
		String cwd = new File("").getAbsolutePath();
		this.file = new File(cwd + FilePath.EVENT_FILE);
		this.timeStamp = file.lastModified();
	}
	/**
	 * Checks if the file is updated
	 * @return true if the file was successfully updated, false otherwise
	 */
	public boolean isFileUpdated() {
		if (timeStamp != file.lastModified()) {
			timeStamp = file.lastModified();
			return true;
		} else {
			return false;
		}
	}
	/**
	 * getter to get the file 
	 * @return type file
	 */
	public File getFile() {
		return file;
	}
}
