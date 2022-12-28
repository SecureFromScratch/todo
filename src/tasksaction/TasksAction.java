package tasksaction;

import java.util.List;
import java.util.Map;

import super_simple_web_server.SuperSimpleWebServer.Request;
import tasks.TasksBundle;

public interface TasksAction {
	TasksBundle doAction(final TasksBundle tasks, final Map<String, Object> params);
	Map<String, Object> validate(final List<String> untrust_params);
}
