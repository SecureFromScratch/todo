package tasksaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tasks.Task;
import tasks.TasksBundle;
import uriparse.TaskParser;

public class CompletionAction implements TasksAction {
	private static final String KEY_TASK = "task";
	private static final String KEY_COMPLETED = "isCompleted";
	
	@Override
	public TasksBundle doAction(TasksBundle tasks, Map<String, Object> params) {
		tasks.getState((Task)params.get(KEY_TASK)).setCompleted((Boolean)params.get(KEY_COMPLETED));
		return tasks;
	}

	@Override
	public Map<String, Object> validate(List<String> untrust_params) {
		if (untrust_params.size() != TaskParser.NUM_ARGS_REQUIRED + 1) {
			throw new IllegalArgumentException("wrong number of parameters " + untrust_params.size() + ", expected " + TaskParser.NUM_ARGS_REQUIRED);
		}
		
		final Map<String, Object> params = new HashMap<>();
		params.put(KEY_TASK, TaskParser.parseTask(untrust_params.get(0), untrust_params.get(1), untrust_params.get(2)));
		final boolean isCompleted = Boolean.valueOf(untrust_params.get(3));
		params.put(KEY_COMPLETED, isCompleted);
		
		return params;
	}

}
