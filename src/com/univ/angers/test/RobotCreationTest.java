package com.univ.angers.test;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import com.univ.angers.GeneralVariables;
import com.univ.angers.Robot;
import com.univ.angers.RobotFactory;

public class RobotCreationTest {

	@Test
	/**
	 * This test verify if, with a constant build robot, the java, class,
	 * properties and jar file are created correctly.
	 */
	public void makeRobotTest() {
		final Robot testRobot = RobotFactory.getInstance().buildTestRobot("TestRobot",
				GeneralVariables.ROBOT_TEST_PACKAGE);

		// The files shouldn't exists at this time

		testRobot.makeRobot();
		try {
			// we wait one second in order to let jar command create the jar
			// before testing if it was create
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		final File robotJava = new File(testRobot.getJavaName());
		Assert.assertTrue(robotJava.exists());

		final File robotClass = new File(testRobot.getClassName());
		Assert.assertTrue(robotClass.exists());

		final File robotProperties = new File(testRobot.getPropertiesName());
		Assert.assertTrue(robotProperties.exists());

		final File robotJar = new File(testRobot.getJarName());
		Assert.assertTrue(robotJar.exists());

		robotJava.delete();
		robotClass.delete();
		robotProperties.delete();
		robotJar.delete();

		Assert.assertFalse(robotJava.exists());
		Assert.assertFalse(robotClass.exists());
		Assert.assertFalse(robotProperties.exists());
		Assert.assertFalse(robotJar.exists());
	}
}
