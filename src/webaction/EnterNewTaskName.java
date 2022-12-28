package webaction;

import super_simple_web_server.SuperSimpleWebServer.Request;
import tasks.TasksBundle;

public class EnterNewTaskName implements WebAction {

	@Override
	public String doAction(Request request, String untrust_remainingUriParams, TasksBundle tasks) {
		return "<form action='/choosedate'>Enter name for task: <input name='name'></form>";
	}

}
