package uriparse;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import tasks.Task;

public class TaskParser {
	public static final int NUM_ARGS_REQUIRED = 3;
	
	public static Task parseTask(final String untrust_name, final String untrust_encodedDate, final String untrust_encodedTime) {
		final String name;
		try {
			name = java.net.URLDecoder.decode(untrust_name, StandardCharsets.UTF_8.name());
		} 
		catch (UnsupportedEncodingException ex) {
			assert ("Impossible: missing utf8 charset encoding" == null);
			throw new UnsupportedOperationException(ex.getMessage());
		}
		LocalDateTime dueTime = LocalDateTime.of(convertToDate(untrust_encodedDate), convertToTime(untrust_encodedTime));
		return new Task(name, dueTime);
	}
	
	private static LocalTime convertToTime(final String strHhMm) {
		if (strHhMm.length() != 4) {
			throw new IllegalArgumentException("time parameter of wrong length. use hhmm.");
		}
	
		int hour = Integer.valueOf(strHhMm.substring(0, 2));
		int minute = Integer.valueOf(strHhMm.substring(2, 4));
		
		return LocalTime.of(hour,  minute);
	}
	
	private static LocalDate convertToDate(final String str) {
		if (str.length() != 8) {
			throw new IllegalArgumentException("date parameter of wrong length. use yyyymmdd.");
		}
	
		int year = Integer.valueOf(str.substring(0, 4));
		int month = Integer.valueOf(str.substring(4, 6));
		int day = Integer.valueOf(str.substring(6, 8));
		
		return LocalDate.of(year, month, day);
	}
}
