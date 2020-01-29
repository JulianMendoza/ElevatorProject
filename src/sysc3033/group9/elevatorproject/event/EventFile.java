package sysc3033.group9.elevatorproject.event;

import java.io.File;

import sysc3033.group9.elevatorproject.constants.FilePath;

public class EventFile {
	private long timeStamp;
	private File file;

	public EventFile() {
		String cwd = new File("").getAbsolutePath();
		this.file = new File(cwd + FilePath.EVENT_FILE);
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
