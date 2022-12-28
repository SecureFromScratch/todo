package tasksaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tasks.TasksBundle;

public class Identity implements TasksAction {
	@Override
	public TasksBundle doAction(TasksBundle tasks, Map<String, Object> params) {
		return tasks;
	}

	@Override
	public Map<String, Object> validate(List<String> untrust_params) {
		return new HashMap<>();
	}

}
