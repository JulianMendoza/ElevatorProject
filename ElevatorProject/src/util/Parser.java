package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to parse a given document in a specific format
 * 
 * @author Julian
 *
 */
public class Parser {
	public static List<String> readTextFile(String fileName){
		List<String> data=new ArrayList<String>();
		File file = new File(fileName);
		try {
			BufferedReader br = new BufferedReader (new FileReader(file));
			String str;
			while((str=br.readLine())!=null) {
				for(String i:str.split(" ")) {
					data.add(i);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
