package webaction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import super_simple_web_server.SuperSimpleWebServer.Request;
import tasks.TasksBundle;

public class ChooseTime implements WebAction {

	@Override
	public String doAction(Request request, String untrust_remainingUriParams, TasksBundle tasks) {
		String page = "<H2>Choose a time:</H2>";
		
		int[] possibleMinutes = { 0, 15, 30, 45 };
		for (int hour = 0 ; hour < 24 ; ++hour) {
			for (int minutes : possibleMinutes) {
				LocalTime time = LocalTime.of(hour, minutes);
				String hhmm = time.format(DateTimeFormatter.ofPattern("HHmm"));
				page += "<a href='/addtask/" + untrust_remainingUriParams +"/" + hhmm
						+ "'>" 
						+ time.format(DateTimeFormatter.ofPattern("HH:mm")) 
						+ "</a>&nbsp;&nbsp;&nbsp;";
			}
			page += "<br>";
		}

		return page;
	}

}
