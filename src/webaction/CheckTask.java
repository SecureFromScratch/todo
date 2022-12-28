package webaction;

import java.time.DateTimeException;
import java.util.Arrays;
import java.util.Map;

import pagegen.BasicParts;
import super_simple_web_server.SuperSimpleWebServer.Request;
import tasks.TasksBundle;
import tasksaction.TasksAction;

public class CheckTask implements WebAction {

	@Override
	public String doAction(final Request request, final String untrust_remainingUriParams, TasksBundle tasks) {
		try {
			int oldSize = tasks.size();

			String[] untrust_params = untrust_remainingUriParams.split("/");
			TasksAction act = new tasksaction.CompletionAction();
			Map<String, Object> params = act.validate(Arrays.asList(untrust_params));
			tasks = act.doAction(tasks, params);
	
			assert (tasks.size() == oldSize); // no change in size
	
			return BasicParts.tasksHeader(tasks) + BasicParts.tasksList(tasks);
		}
		catch (DateTimeException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

}
