package tasksaction;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tasks.MutableState;
import tasks.Task;
import tasks.TasksBundle;
import tasks.TasksBundleInMemory;
import tasksaction.AddTaskAction;

class AddTaskTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void addingOneTask() {
		TasksBundle tasks = new TasksBundleInMemory();
		assertEquals(0, tasks.size());

		List<String> input = Arrays.asList("MyTask", "20221225", "1251");
		AddTaskAction act = new AddTaskAction();
		Map<String, Object> params = act.validate(input);
		tasks = act.doAction(tasks, params);

		// ensure task was added
		assertEquals(1, tasks.size());
		
		// Ensure iterator works
		Iterator<Entry<Task, MutableState>> itr = tasks.iterator();
		assertTrue(itr.hasNext());

		// Ensure added task has correct details
		Entry<Task, MutableState> newTask = itr.next();
		assertTrue(!itr.hasNext());
		assertEquals("MyTask", newTask.getKey().getName());
		assertEquals(LocalDateTime.of(2022, 12, 25, 12, 51), newTask.getKey().getDueTime());
	}
}
