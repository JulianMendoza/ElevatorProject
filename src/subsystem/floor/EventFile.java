package subsystem.floor;

import java.io.File;

import constants.FloorID;

public class EventFile {
	private long timeStamp;
	private File file;

	public EventFile() {
		String cwd = new File("").getAbsolutePath();
		this.file = new File(cwd + FloorID.EVENTFILE);
		this.timeStamp = file.lastModified();
	}

	public boolean isFileUpdated() {
		if (timeStamp != file.lastModified()) {
			timeStamp = file.lastModified();
			return true;
		} else {
			return false;
		}
	}

	public File getFile() {
		return file;
	}
}
