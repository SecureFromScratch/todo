package webaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.List;

import super_simple_web_server.SuperSimpleWebServer.Request;
import tasks.TasksBundle;

public class ChooseDate implements WebAction {
	private static final int NUM_DAYS_TO_DISPLAY = 30;

	@Override
	public String doAction(Request request, String untrust_remainingUriParams, TasksBundle tasks) {
		String page = "<H2>Choose a date:</H2>";
		LocalDate endDate = LocalDate.now().plus(NUM_DAYS_TO_DISPLAY, ChronoUnit.DAYS);
		
		for (LocalDate date = LocalDate.now() ; date.isBefore(endDate) ; date = date.plus(1, ChronoUnit.DAYS)) {
			page += "<a href='/choosetime/" + request.getParams().get("name") +"/" + date.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
					+ "'>" 
					+ date.format(DateTimeFormatter.ISO_LOCAL_DATE) 
					+ "</a><br>";
		}

		return page;
	}

}
