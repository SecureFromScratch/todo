package tasks;

public class TaskAlreadyExistsException extends RuntimeException {
	private final Task m_task;
	
	public TaskAlreadyExistsException(final Task task) {
		m_task = task;
	}
}
