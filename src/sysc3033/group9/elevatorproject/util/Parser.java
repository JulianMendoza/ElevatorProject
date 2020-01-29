package sysc3033.group9.elevatorproject.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

import sysc3033.group9.elevatorproject.event.FloorEvent;

/**
 * Utility class to parse a given document in a specific format
 * 
 * @author Julian
 *
 */
public class Parser {

	public static FloorEvent readTextFile(String fileName, File file) {
		FloorEvent event = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String str;
			while ((str = br.readLine()) != null) {
				event = new FloorEvent(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return event;
	}

	public static void deparse(String fileName, String event) {
		String cwd = new File("").getAbsolutePath();
		cwd += fileName;
		FileWriter writer = null;
		try {
			writer = new FileWriter(cwd);
			writer.write(event);
			System.out.println(event + " generated in directory: " + cwd);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}