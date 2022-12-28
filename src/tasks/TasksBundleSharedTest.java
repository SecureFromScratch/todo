package tasks;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

abstract class TasksBundleSharedTest {
	protected abstract TasksBundle createTasksBundle(); // factory method
	private Logger m_logger = Logger.getLogger(this.getClass().getCanonicalName());

	@Test
	void empty() {
		final TasksBundle tasks = createTasksBundle();
		
		assertTrue(tasks.isEmpty());
		assertEquals(0, tasks.size());
		assertTrue(!tasks.iterator().hasNext());
		//.....
	}

	@Test
	void addDuplicate() {
		final Task t = new Task("abc", LocalDateTime.now());
		
		final TasksBundle tasks = createTasksBundle();
		tasks.add(t);
		
		assertThrowsExactly(TaskAlreadyExistsException.class, new AddTaskExecutable(tasks, t));

		// lambda
		assertThrowsExactly(TaskAlreadyExistsException.class, ()->{ 
			Logger logger = Logger.getLogger(this.getClass().getCanonicalName());
			logger.log(Level.INFO, "Adding task " + t);

			tasks.add(t); 
		});

		// Anonymous class
		assertThrowsExactly(TaskAlreadyExistsException.class, new Executable() {
			private void writeToLogger() {
				Logger logger = Logger.getLogger(this.getClass().getCanonicalName());
				logger.log(Level.INFO, "Adding task " + t);
			}

			@Override
			public void execute() throws Throwable {
				writeToLogger();
				tasks.add(t); 
			} 
		});
	}

	// inner class / nested class
	private class AddTaskExecutable implements Executable {
		private final TasksBundle tasks;
		private final Task t;
		
		public AddTaskExecutable(final TasksBundle tasks, final Task t) {
			this.tasks = tasks;
			this.t = t;
		}
		
		private void writeToLogger() {
			m_logger.log(Level.INFO, "Adding task " + t);
		}

		@Override
		public void execute() throws Throwable {
			writeToLogger();
			tasks.add(t);
		}
	}

}
