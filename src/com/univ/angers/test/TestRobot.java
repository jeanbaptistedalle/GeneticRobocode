package com.univ.angers.test;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import com.univ.angers.GeneralVariables;
import com.univ.angers.Robot;
import com.univ.angers.RobotFactory;

public class TestRobot {

	@Test
	/**
	 * This test verify if, with a constant build robot, the java, class,
	 * properties and jar file are created correctly.
	 */
	public void makeRobotTest() {
		final Robot testRobot = RobotFactory.getInstance().buildTestRobot("TestRobot",
				GeneralVariables.ROBOT_TEST_PACKAGE);

		// The files shouldn't exists at this time
		final File robotJava = new File(
				GeneralVariables.ROBOTS_FOLDER + GeneralVariables.ROBOT_TEST_PACKAGE + "/" + testRobot.getJavaName());
		if (robotJava.exists()) {
			robotJava.delete();
		}

		final File robotClass = new File(
				GeneralVariables.ROBOTS_FOLDER + GeneralVariables.ROBOT_TEST_PACKAGE + "/" + testRobot.getClassName());
		if (robotClass.exists()) {
			robotJava.delete();
		}

		final File robotProperties = new File(GeneralVariables.ROBOTS_FOLDER + GeneralVariables.ROBOT_TEST_PACKAGE + "/"
				+ testRobot.getPropertiesName());
		if (robotProperties.exists()) {
			robotJava.delete();
		}

		final File robotJar = new File(
				GeneralVariables.ROBOTS_FOLDER + GeneralVariables.ROBOT_TEST_PACKAGE + "/" + testRobot.getJarName());
		if (robotJar.exists()) {
			robotJava.delete();
		}

		testRobot.makeRobot();
		try {
			// we wait one second in order to let jar command create the jar
			// before testing if it was create
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		Assert.assertTrue(robotJava.exists());
		Assert.assertTrue(robotClass.exists());
		Assert.assertTrue(robotProperties.exists());
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
