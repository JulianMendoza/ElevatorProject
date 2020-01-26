package subsystem.floor;

import java.util.List;
import util.Parser;

public class Floor implements Runnable{
	public Floor() {
	}
	public void read(String fileName) {
		List<String> data = Parser.readTextFile(fileName);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
