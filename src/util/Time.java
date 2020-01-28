package util;

import java.text.ParseException;

public class Time {
	private String time;
	private int hours;
	private int minutes;
	private int seconds;
	private int milliseconds;
	
	public Time(String time) throws ParseException{
		parse(time);
	}
	
	private void parse(String time) throws ParseException {
		this.time=time;
		String[] colonSplit = time.split(":");
		if(colonSplit.length == 3) {
	
			String[] periodSplit = colonSplit[2].split("\\.");
			if(periodSplit.length == 2) {
				try {
					hours = Integer.parseInt(colonSplit[0]);
					minutes = Integer.parseInt(colonSplit[1]);
					seconds = Integer.parseInt(periodSplit[0]);
					milliseconds = Integer.parseInt(periodSplit[1]);
				} catch(NumberFormatException e) {
					throw new ParseException("Invalid time", 0);
				}
			} else {
				throw new ParseException("Invalid time", 0);
			}
		} else {
			throw new ParseException("Invalid time", 0);
		}
		
	}
	

	public int getMilliseconds() {
		return milliseconds;
	}

	public int getHours() {
		return hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public int getSeconds() {
		return seconds;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		} else if (o == this){
			return true;
		} else if (o instanceof Time){
			Time time = (Time) o;
			return time.getHours() == hours && time.getMinutes() == minutes && time.getSeconds() == seconds && time.getMilliseconds() == milliseconds;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return 17 * minutes + hours * 3 * milliseconds + seconds * 7;
	}
	@Override
	public String toString() {
		return time;
	}
}
