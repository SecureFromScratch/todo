package webaction;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DateTimeException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import super_simple_web_server.SuperSimpleWebServer;
import tasks.TaskAlreadyExistsException;
import tasks.TasksBundle;
import tasks.TasksBundleInMemory;

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

	private static int countTasks(final String page) {
		int curPos = 0;
		int numTasks = 0;
		while (true) {
			int findPos = page.indexOf("<div>", curPos);
			if (findPos == -1) {
				break;
			}
			
			++numTasks;
			curPos = findPos + "<div>".length();
		}
		
		return numTasks;
	}
	
	@Test
	void testAddingOneTask() {
		final AddTask action = new AddTask();
		final TasksBundle tasks = new TasksBundleInMemory();

		Assertions.assertTrue(tasks.isEmpty());
		
		final String page = action.doAction(null, "Buy%20bananas/20221225/1345", tasks);

		Assertions.assertEquals(1, countTasks(page));
	}

	@Test
	void testAddingSeveralTask() {
		final AddTask action = new AddTask();
		final TasksBundle tasks = new TasksBundleInMemory();

		action.doAction(null, "Buy%20bananas/20221225/1345", tasks);
		action.doAction(null, "BuyMilk/20221225/1300", tasks);
		final String page = action.doAction(null, "PayWater/20221227/1900", tasks);

		Assertions.assertEquals(3, countTasks(page));
	}

	@Test
	void testAddingDuplicateTask() {
		final AddTask action = new AddTask();
		final TasksBundle tasks = new TasksBundleInMemory();

		action.doAction(null, "Buy%20bananas/20221225/1345", tasks);
		try {
			action.doAction(null, "Buy%20bananas/20221225/1345", tasks);
			Assertions.fail("Did not throw exception for duplicate task");
		}
		catch (TaskAlreadyExistsException ex) {
			// all is good
		}
	}


	private static void testExceptionInParamsString(final String params) {
		final AddTask action = new AddTask();
		final TasksBundle tasks = new TasksBundleInMemory();

		Assertions.assertTrue(tasks.isEmpty());
		
		try {
			final String page = action.doAction(null, params, tasks);
			Assertions.fail("IllegalArgumentException not thrown for string " + params);
		}
		catch (IllegalArgumentException ex) {
			// all is good
		}
	}

	@Test
	void testEmptyString() {
		testExceptionInParamsString("");
	}

	@Test
	void testMissingTime() {
		testExceptionInParamsString("Buy%20bananas/20221225");
	}

	@Test
	void testEmptyTime() {
		testExceptionInParamsString("Buy%20bananas/20221225/");
	}

	@Test
	void testBadTime2401() {
		testExceptionInParamsString("Buy%20bananas/20221225/2401");
	}

	@Test
	void testBadTime2500() {
		testExceptionInParamsString("Buy%20bananas/20221225/2500");
	}

	@Test
	void testNonNumericTime() {
		testExceptionInParamsString("Buy%20bananas/20221225/a401");
	}

	@Test
	void testTooLongTime() {
		testExceptionInParamsString("Buy%20bananas/20221225/040100");
	}
}
