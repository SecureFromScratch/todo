package tasks;

import java.util.Iterator;
import java.util.Map.Entry;

public interface TasksBundle extends Iterable<Entry<Task, MutableState>> {
	void add(final Task task);
	Iterator<Entry<Task, MutableState>> iterator();
	boolean isEmpty();
	int size();
	MutableState getState(Task task);
}
