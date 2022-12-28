package tasksaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tasks.Task;
import tasks.TasksBundle;
import uriparse.TaskParser;

public class AddTaskAction implements TasksAction {
	private static final String KEY_TASK = "task";
	
	@Override
	public TasksBundle doAction(final TasksBundle tasks, final Map<String, Object> params) {
		tasks.add((Task)params.get(KEY_TASK));
		return tasks;
	}
	
	@Override
	public Map<String, Object> validate(final List<String> untrust_params) {
		if (untrust_params.size() != TaskParser.NUM_ARGS_REQUIRED) {
			throw new IllegalArgumentException("wrong number of parameters " + untrust_params.size() + ", expected " + TaskParser.NUM_ARGS_REQUIRED);
		}
		
		final Map<String, Object> params = new HashMap<>();
		params.put(KEY_TASK, TaskParser.parseTask(untrust_params.get(0), untrust_params.get(1), untrust_params.get(2)));
		return params;
	}
}
