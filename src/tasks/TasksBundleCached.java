package tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import tasksaction.TasksAction;

public class TasksBundleCached implements TasksBundle {
	private final TasksBundleOnDisk m_onDisk = new TasksBundleOnDisk();
	private Optional<TasksBundleInMemory> m_inMemory = Optional.empty();
	
	private void initializeInMemory() {
		m_inMemory = Optional.of(new TasksBundleInMemory());
		for (Entry<Task, MutableState> item : m_onDisk) {
			m_inMemory.get().add(item.getKey());
			if (item.getValue().isCompleted()) {
				m_inMemory.get().getState(item.getKey()).setCompleted(true);
			}
		}
	}
	
	private void ensureInMemoryIsInitialized() {
		if (m_inMemory.isEmpty()) {
			initializeInMemory();
		}
		assert (m_inMemory.isPresent());
	}
	
	@Override
	public void add(Task task) {
		ensureInMemoryIsInitialized();
		m_onDisk.add(task);
		m_inMemory.get().add(task);
	}

	@Override
	public Iterator<Entry<Task, MutableState>> iterator() {
		ensureInMemoryIsInitialized();
		return m_inMemory.get().iterator();
	}

	@Override
	public boolean isEmpty() {
		ensureInMemoryIsInitialized();
		assert m_onDisk.isEmpty() == m_inMemory.isEmpty();
		return m_inMemory.isEmpty();
	}

	@Override
	public int size() {
		ensureInMemoryIsInitialized();
		return m_inMemory.get().size();
	}

	@Override
	public MutableState getState(Task task) {
		throw new UnsupportedOperationException();
	}
}
