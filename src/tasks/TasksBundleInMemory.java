package tasks;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public final class TasksBundleInMemory implements TasksBundle {
	private final Map<Task, MutableState> m_tasks = new HashMap<>();
	
	@Override
	public final void add(final Task task) {
		if (m_tasks.containsKey(task)) {
			throw new TaskAlreadyExistsException(task);
		}
		final int oldNumTasks = m_tasks.size();
		m_tasks.put(task, new MutableState());
		assert (m_tasks.size() == oldNumTasks + 1);
	}
	
	@Override
	public final Iterator<Entry<Task, MutableState>> iterator() {
		return m_tasks.entrySet().iterator();
	}

	@Override
	public final boolean isEmpty() {
		return m_tasks.isEmpty();
	}

	@Override
	public final int size() {
		return m_tasks.size();
	}

	@Override
	public final MutableState getState(Task task) {
		if (!m_tasks.containsKey(task)) {
			throw new IllegalArgumentException("task not found: " + task.toString());
		}
		
		return m_tasks.get(task);
	}
}
