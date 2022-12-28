package uriparse;

import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import tasks.Task;

public final class TaskEncoder {
	public static String encode(final Task task) {
		return URLEncoder.encode(task.getName()) + "/" + encodeAsString(task.getDueTime());
	}
	
	private static String encodeAsString(final LocalDateTime date) {
		return date.format(DateTimeFormatter.ofPattern("yyyyMMdd/HHmm"));
	}
}
