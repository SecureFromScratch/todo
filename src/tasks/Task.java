package tasks;

import java.time.LocalDateTime;

public final class Task {
	private final String m_name;
	private final LocalDateTime m_dueTime;

	public Task(final String name, final LocalDateTime dueTime) {
		assert (name != null);
		assert (dueTime != null);
		
		m_name = name;
		m_dueTime = dueTime;
	}
	
	public final String getName() {
		return m_name;
	}

	public final LocalDateTime getDueTime() {
		return m_dueTime;
	}
	
	@Override
	public final int hashCode() {
		return m_name.hashCode() * m_dueTime.hashCode();
	}
	
	@Override
	public final boolean equals(final Object other) {
		if ((other == null) && !(other instanceof Task)) {
			return false;
		}
		
		final Task otherTask = (Task)other;
		return this.getName().equals(otherTask.getName()) && this.getDueTime().equals(otherTask.getDueTime());
	}
	
	@Override
	public String toString() {
		return getName() + " @ " + getDueTime();
	}
}
