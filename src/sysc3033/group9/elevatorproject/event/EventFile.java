package sysc3033.group9.elevatorproject.event;

import java.io.File;
import java.io.Serializable;

import sysc3033.group9.elevatorproject.constants.FilePath;

/**
 * This file represents an elevator event; It simulates an elevator stop and go
 * request
 * 
 * @author Julian Mendoza
 */
public class EventFile implements Serializable {
	private static final long serialVersionUID = 1L;
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
	 * 
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

	public void test() {
		System.out.println(this.timeStamp);
	}

	/**
	 * getter to get the file
	 * 
	 * @return type file
	 */
	public File getFile() {
		return file;
	}
}
