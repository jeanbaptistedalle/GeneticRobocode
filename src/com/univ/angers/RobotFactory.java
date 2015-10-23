package com.univ.angers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * @author etudiant
 *
 */
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

		final Robot robot = new Robot();
		robot.setRobotName(robotName);
		robot.setRobotCode(robotCode.toString());
		robot.setRobotPackage(robotPackage);

		final String fileName = GeneralVariables.ROBOTS_FOLDER + robot.getFormatedPackage() + robotName + ".java";
		final File f = new File(fileName);
		try {
			final Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
			writer.write(robotCode.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		robot.makeRobot();
		return robot;
	}

	/**
	 * 
	 * Method which build a complete robot with the generated code
	 * 
	 * @param robotName
	 * @return
	 */
	public Robot buildGenRobot(final String robotName, final String robotPackage, final String[] code) {
		final StringBuilder robotCode = new StringBuilder();
		if (robotPackage != null && robotPackage != "") {
			robotCode.append("package ");
			robotCode.append(robotPackage).append(";").append(GeneralVariables.NEW_LINE);
		}
		robotCode.append("import robocode.*;").append(GeneralVariables.NEW_LINE);
		robotCode.append("public class ");
		robotCode.append(robotName);
		robotCode.append(" extends Robot {").append(GeneralVariables.NEW_LINE);

		// run definition
		robotCode.append("public void run() {").append(GeneralVariables.NEW_LINE);
		robotCode.append("while (true) {").append(GeneralVariables.NEW_LINE);
		robotCode.append(code[0]);
		robotCode.append("}").append(GeneralVariables.NEW_LINE);
		robotCode.append("}").append(GeneralVariables.DOUBLE_LINE);

		// onScannedRobot definition
		robotCode.append("public void onScannedRobot(ScannedRobotEvent event) {").append(GeneralVariables.NEW_LINE);
		if (code.length < 2) {
			robotCode.append("");
		} else {
			robotCode.append(code[1]);
		}
		robotCode.append("}").append(GeneralVariables.DOUBLE_LINE);

		// onHitWall definition
		robotCode.append("public void onHitWall(HitWallEvent event) {").append(GeneralVariables.NEW_LINE);
		if (code.length < 3) {
			robotCode.append("");
		} else {
			robotCode.append(code[2]);
		}
		robotCode.append("}").append(GeneralVariables.DOUBLE_LINE);

		// onHitByBullet definition
		robotCode.append("public void onHitByBullet(HitByBulletEvent event) {").append(GeneralVariables.NEW_LINE);
		if (code.length < 4) {
			robotCode.append("");
		} else {
			robotCode.append(code[3]);
		}
		robotCode.append("}").append(GeneralVariables.DOUBLE_LINE);

		robotCode.append("}").append(GeneralVariables.NEW_LINE);

		final Robot robot = new Robot();
		robot.setRobotName(robotName);
		robot.setRobotCode(robotCode.toString());
		robot.setRobotPackage(robotPackage);

		final String fileName = GeneralVariables.ROBOTS_FOLDER + robot.getFormatedPackage() + robotName + ".java";
		final File f = new File(fileName);
		try {
			final Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
			writer.write(robotCode.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		robot.makeRobot();
		return robot;
	}

	public static RobotFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RobotFactory();
		}
		return INSTANCE;
	}
}
