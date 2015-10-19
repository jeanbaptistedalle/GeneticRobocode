package com.univ.angers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class RobotFactory {

	private static RobotFactory INSTANCE = null;

	private RobotFactory() {

	}

	/**
	 * Method which create a test robot code in order to obtain a .java
	 * 
	 * @param robotName
	 * @return the code
	 */
	public Robot buildTestRobot(final String robotName, final String robotPackage) {
		final StringBuilder robotCode = new StringBuilder();
		if (robotPackage != null && robotPackage != "") {
			robotCode.append("package ");
			robotCode.append(robotPackage).append(";").append(GeneralVariables.NEW_LINE);
		}
		robotCode.append("import robocode.*;").append(GeneralVariables.NEW_LINE);
		robotCode.append("public class ");
		robotCode.append(robotName);
		robotCode.append(" extends Robot {").append(GeneralVariables.NEW_LINE);

		robotCode.append("public void run() {").append(GeneralVariables.NEW_LINE);
		robotCode.append("while (true) {").append(GeneralVariables.NEW_LINE);
		robotCode.append("ahead(100);").append(GeneralVariables.NEW_LINE);
		robotCode.append("turnGunRight(360);").append(GeneralVariables.NEW_LINE);
		robotCode.append("back(100);").append(GeneralVariables.NEW_LINE);
		robotCode.append("turnGunRight(360);").append(GeneralVariables.NEW_LINE);
		robotCode.append("}").append(GeneralVariables.NEW_LINE);
		robotCode.append("}").append(GeneralVariables.NEW_LINE);
		robotCode.append("public void onScannedRobot(ScannedRobotEvent e) {").append(GeneralVariables.NEW_LINE);
		robotCode.append("fire(1);").append(GeneralVariables.NEW_LINE);
		robotCode.append("}").append(GeneralVariables.NEW_LINE);
		robotCode.append("}").append(GeneralVariables.NEW_LINE);
		final String fileName;
		if (robotPackage != null && robotPackage != "") {
			fileName = GeneralVariables.ROBOTS_FOLDER + robotPackage + "/" + robotName + ".java";
		} else {
			fileName = GeneralVariables.ROBOTS_FOLDER + robotName + ".java";
		}
		final File f = new File(fileName);
		try {
			final Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
			writer.write(robotCode.toString());
			writer.close();
		} catch (IOException e) {
			// TODO
		}
		final Robot robot = new Robot();
		robot.setRobotName(robotName);
		robot.setRobotCode(robotCode.toString());
		robot.setRobotPackage(robotPackage);
		return robot;
	}

	/**
	 * Method which create the code of a first gen robot with some random
	 * behaviors
	 * 
	 * @param robotName
	 * @return
	 */
	public Robot buildFirstGenRobot(final String robotName, final String robotPackage) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * Method which create the code of a next gen robot with some part of his
	 * mother and father
	 * 
	 * @param robotName
	 * @param mother
	 * @param father
	 * @return
	 */
	public Robot buildNextGenRobot(final String robotName, final String robotPackage, final Robot mother,
			final Robot father) {
		// TODO Auto-generated method stub
		return null;
	}

	public static RobotFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RobotFactory();
		}
		return INSTANCE;
	}
}
