package jgap.gp.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import com.univ.angers.GeneralVariables;
import com.univ.angers.Robot;

/**
 * @author etudiant
 *
 */
public class GPRobotFactory {

	private static GPRobotFactory INSTANCE = null;

	private GPRobotFactory() {

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
		robotCode.append("double i = 10;").append(GeneralVariables.NEW_LINE);

		// run definition
		robotCode.append("public void run() {").append(GeneralVariables.NEW_LINE);
		robotCode.append("while (true) {").append(GeneralVariables.NEW_LINE);
		// robotCode.append("turnGunRight(100000);").append(GeneralVariables.NEW_LINE);
		robotCode.append(code[0]).append(GeneralVariables.NEW_LINE);
		robotCode.append("}").append(GeneralVariables.NEW_LINE);
		robotCode.append("}").append(GeneralVariables.DOUBLE_LINE);

		String[] onEventMethods = new ArrayList<String>() {
			private static final long serialVersionUID = -2994933712927736914L;
			{
				add("public void onScannedRobot(ScannedRobotEvent event) {");
				add("public void onHitWall(HitWallEvent event) {");
				add("public void onHitByBullet(HitByBulletEvent event) {");
				add("public void onBulletHit(BulletHitEvent event) {");
				add("public void onBulletMissed(BulletMissedEvent event) {");
			}
		}.toArray(new String[0]);
		int cpt = 1;
		for (final String onEventMethod : onEventMethods) {
			robotCode.append(onEventMethod).append(GeneralVariables.NEW_LINE);
			if (code.length < cpt + 1) {
				robotCode.append("");
			} else {
				robotCode.append(code[cpt]);
			}
			robotCode.append("}").append(GeneralVariables.DOUBLE_LINE);
			cpt++;
		}

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

	public static GPRobotFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new GPRobotFactory();
		}
		return INSTANCE;
	}
}
