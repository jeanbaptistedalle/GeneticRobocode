package com.univ.angers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

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
		robotCode.append(" extends AdvancedRobot {").append(GeneralVariables.NEW_LINE);
		//les differentes variable utilisable
		
		//variable pour tracker strategie de reste sur sa cible
		robotCode.append("int count = 0; // Keeps track of how long we've").append(GeneralVariables.NEW_LINE);
		robotCode.append("double gunTurnAmt = 10; // How much to turn our gun when searching").append(GeneralVariables.NEW_LINE);
		robotCode.append("String trackName = null; // Name of the robot we're currently tracking").append(GeneralVariables.NEW_LINE);
		
		//variable Walls strategie tourne autour du terrain
		robotCode.append("boolean peek = false; // Don't turn if there's a robot there").append(GeneralVariables.NEW_LINE);
		robotCode.append("double moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight()); // How much to move").append(GeneralVariables.NEW_LINE);
		
		//variable Corner
		robotCode.append("int others; // Number of other robots in the game").append(GeneralVariables.NEW_LINE);
		robotCode.append("static int corner = 0; // Which corner we are currently using").append(GeneralVariables.NEW_LINE);
		robotCode.append("boolean stopWhenSeeRobot = false; // See goCorner()").append(GeneralVariables.NEW_LINE);
		
		//variable Fire
		robotCode.append("int dist = 50;").append(GeneralVariables.NEW_LINE);
		
		//variable ramfire
		robotCode.append(" int turnDirection = 1; // Clockwise or counterclockwise").append(GeneralVariables.NEW_LINE);
		
		//variable circlingBot tourne autour de la cible
		robotCode.append("boolean movingForward; // Is set to true when setAhead is called, set to false on setBack").append(GeneralVariables.NEW_LINE);
		robotCode.append("boolean inWall; // Is true when robot is near the wall.").append(GeneralVariables.NEW_LINE);
		
		// run definition
		robotCode.append("public void run() {").append(GeneralVariables.NEW_LINE);
		robotCode.append(code[0]).append(GeneralVariables.NEW_LINE);
		
		robotCode.append("while (true) {").append(GeneralVariables.NEW_LINE);
		robotCode.append(code[1]);
		robotCode.append("}").append(GeneralVariables.NEW_LINE);
		robotCode.append("}").append(GeneralVariables.DOUBLE_LINE);

		/*String[] onEventMethods = new ArrayList<String>() {{
		    add("public void onScannedRobot(ScannedRobotEvent e) {");
		    add("public void onHitRobot(HitRobotEvent e) {");
		    add("public void onHitWall(HitWallEvent event) {");
		    add("public void onHitByBullet(HitByBulletEvent event) {");
		}}.toArray(new String[0]);
		int cpt = 1;
		for(final String onEventMethod : onEventMethods){
			robotCode.append(onEventMethod).append(GeneralVariables.NEW_LINE);
			if (code.length < cpt+1) {
				robotCode.append("");
			} else {
				robotCode.append(code[cpt]);
			}
			robotCode.append("}").append(GeneralVariables.DOUBLE_LINE);
			cpt++;
		}*/
		
		robotCode.append("public void onScannedRobot(ScannedRobotEvent e) {").append(GeneralVariables.NEW_LINE);
		robotCode.append(code[2]);
		robotCode.append("}").append(GeneralVariables.DOUBLE_LINE);
		
		robotCode.append("public void onScannedRobot(HitRobotEvent e) {").append(GeneralVariables.NEW_LINE);
		robotCode.append(code[3]);
		robotCode.append("}").append(GeneralVariables.DOUBLE_LINE);
		
		robotCode.append("public void onScannedRobot(HitWallEvent e) {").append(GeneralVariables.NEW_LINE);
		robotCode.append(code[4]);
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
