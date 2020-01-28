package subsystem.floor;

import java.io.File;

import constants.FloorID;

public class EventFile {
	private long timeStamp;
	private File file;
	public EventFile() {
		this.file=new File(FloorID.EVENTFILE);
		this.timeStamp=file.lastModified();
		System.out.println(file.exists());
	}
	public boolean isFileUpdated() {
		System.out.println(file.lastModified());
		if(timeStamp!=file.lastModified()) {
			timeStamp=file.lastModified();
			return true;
		}else {
			return false;
		}
	}
	public File getFile() {
		return file;
	}
}
