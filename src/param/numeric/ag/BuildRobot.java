package param.numeric.ag;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.univ.angers.GeneralVariables;
import com.univ.angers.Robot;

public class BuildRobot {
	
	
	public BuildRobot() {

	}
	
	public Robot build(final String robotName, final String robotPackage, final String params) {
		final StringBuilder robotCode = new StringBuilder();
		if (robotPackage != null && robotPackage != "") {
			robotCode.append("package ");
			robotCode.append(robotPackage).append(";").append(GeneralVariables.NEW_LINE);
		}
		robotCode.append("import robocode.Robot;").append(GeneralVariables.NEW_LINE);
		robotCode.append("import robocode.ScannedRobotEvent;").append(GeneralVariables.NEW_LINE);
		robotCode.append("import robocode.HitByBulletEvent;").append(GeneralVariables.NEW_LINE);
		robotCode.append("public class ");
		robotCode.append(robotName);
		robotCode.append(" extends Robot {").append(GeneralVariables.NEW_LINE);
		robotCode.append(params).append(GeneralVariables.NEW_LINE);
		robotCode.append("public void run() {").append(GeneralVariables.NEW_LINE);
		robotCode.append("while (true) {").append(GeneralVariables.NEW_LINE);
		robotCode.append("ahead(param_a);").append(GeneralVariables.NEW_LINE);
		robotCode.append("turnGunRight(param_b);").append(GeneralVariables.NEW_LINE);
		robotCode.append("back(param_c);").append(GeneralVariables.NEW_LINE);
		robotCode.append("turnGunRight(param_d);").append(GeneralVariables.NEW_LINE);
		robotCode.append("}").append(GeneralVariables.NEW_LINE);
		robotCode.append("}").append(GeneralVariables.NEW_LINE);
		robotCode.append("public void onScannedRobot(ScannedRobotEvent e) {").append(GeneralVariables.NEW_LINE);
		robotCode.append("fire(param_e);").append(GeneralVariables.NEW_LINE);
		robotCode.append("}").append(GeneralVariables.NEW_LINE);
		robotCode.append("public void onHitByBullet(HitByBulletEvent e) {").append(GeneralVariables.NEW_LINE);
		robotCode.append("turnLeft(param_f - e.getBearing());").append(GeneralVariables.NEW_LINE);
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

}
