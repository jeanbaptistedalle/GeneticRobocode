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
			robotCode.append(robotPackage).append(";").append(GeneralVariables.DOUBLE_LINE);
		}
		robotCode.append("import robocode.*;").append(GeneralVariables.NEW_LINE);
		robotCode.append("import java.awt.geom.Point2D;").append(GeneralVariables.DOUBLE_LINE);

		robotCode.append("public class ");
		robotCode.append(robotName);
		robotCode.append(" extends AdvancedRobot {").append(GeneralVariables.NEW_LINE);
		addVariable(robotCode, robotName);
		// run definition
		robotCode.append("public void run() {").append(GeneralVariables.NEW_LINE);
		robotCode.append("		i = 1;").append(GeneralVariables.NEW_LINE);
		robotCode.append("		center = new Point2D.Double(getBattleFieldWidth() /2, getBattleFieldHeight() /2);")
				.append(GeneralVariables.NEW_LINE);
		robotCode.append("		enemy = null;").append(GeneralVariables.NEW_LINE);
		robotCode.append("while (true) {").append(GeneralVariables.NEW_LINE);
		robotCode.append(code[0]).append(GeneralVariables.NEW_LINE);
		robotCode.append("}").append(GeneralVariables.NEW_LINE);
		robotCode.append("}").append(GeneralVariables.DOUBLE_LINE);

		String[] onEventMethods = new ArrayList<String>() {
			private static final long serialVersionUID = -2994933712927736914L;

			{
				add("public void onScannedRobot(ScannedRobotEvent event) {\n" + "double angleToEnemy = event.getBearing();\n"
						+ "	double angle = Math.toRadians(getHeading() + angleToEnemy % 360);\n"
						+ "	double enemyX = (getX() + Math.sin(angle) * event.getDistance());\n"
						+ "	double enemyY = (getY() + Math.cos(angle) * event.getDistance());\n"
						+ "	enemy = new Point2D.Double(enemyX, enemyY);\n");
				add("public void onHitWall(HitWallEvent event) {");
				add("public void onHitByBullet(HitByBulletEvent event) {");
				add("public void onBulletHit(BulletHitEvent event) {");
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
		addUsefulMethods(robotCode);
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

	private static void addVariable(final StringBuilder robotCode, final String robotName) {
		robotCode
				.append("/******************************************************************************************************************\\")
				.append(GeneralVariables.NEW_LINE);
		robotCode
				.append("********************************************Variables  !************************************************************")
				.append(GeneralVariables.NEW_LINE);
		robotCode
				.append("\\******************************************************************************************************************/")
				.append(GeneralVariables.DOUBLE_LINE);

		robotCode.append("	double i;").append(GeneralVariables.NEW_LINE);
		robotCode.append("	Point2D.Double center = null;").append(GeneralVariables.NEW_LINE);
		robotCode.append("	Point2D.Double enemy = null;").append(GeneralVariables.DOUBLE_LINE);

		robotCode
				.append("/******************************************************************************************************************\\")
				.append(GeneralVariables.NEW_LINE);
		robotCode
				.append("***************************************Generated actions !**********************************************************")
				.append(GeneralVariables.NEW_LINE);
		robotCode
				.append("\\******************************************************************************************************************/")
				.append(GeneralVariables.DOUBLE_LINE);
	}

	private static void addUsefulMethods(final StringBuilder robotCode) {
		robotCode
				.append("/******************************************************************************************************************\\")
				.append(GeneralVariables.NEW_LINE);
		robotCode
				.append("*****************************************Useful commands !**********************************************************")
				.append(GeneralVariables.NEW_LINE);
		robotCode
				.append("\\******************************************************************************************************************/")
				.append(GeneralVariables.DOUBLE_LINE);


		robotCode.append("	public void onBattleEnded(BattleEndedEvent event){").append(GeneralVariables.NEW_LINE);
		robotCode.append("		i = 1;").append(GeneralVariables.NEW_LINE);
		robotCode.append("		enemy = null;").append(GeneralVariables.NEW_LINE);
		robotCode.append("	}").append(GeneralVariables.DOUBLE_LINE);
		
		robotCode.append("	public void aheadDistanceToCenter(){").append(GeneralVariables.NEW_LINE);
		robotCode.append("		ahead(getRobotLocation().distance(center));").append(GeneralVariables.NEW_LINE);
		robotCode.append("	}").append(GeneralVariables.DOUBLE_LINE);
		
		robotCode.append("	public void backDistanceToCenter(){").append(GeneralVariables.NEW_LINE);
		robotCode.append("		back(getRobotLocation().distance(center));").append(GeneralVariables.NEW_LINE);
		robotCode.append("	}").append(GeneralVariables.DOUBLE_LINE);

		robotCode.append("	public void turnToCenter(){").append(GeneralVariables.NEW_LINE);
		robotCode
				.append("	   setTurnRightRadians(normalRelativeAngleRadians(absoluteBearingRadians(getRobotLocation(), center) - getHeadingRadians()));")
				.append(GeneralVariables.NEW_LINE);
		robotCode.append("	}").append(GeneralVariables.DOUBLE_LINE);

		robotCode.append("	public void turnGunToCenter(){").append(GeneralVariables.NEW_LINE);
		robotCode
				.append("	   setTurnRightRadians(normalRelativeAngleRadians(absoluteBearingRadians(getRobotLocation(), center) - getGunHeadingRadians()));")
				.append(GeneralVariables.NEW_LINE);
		robotCode.append("	}").append(GeneralVariables.DOUBLE_LINE);

		robotCode.append("	public void aheadDistanceToEnemy(){").append(GeneralVariables.NEW_LINE);
		robotCode.append("		if(enemy != null){").append(GeneralVariables.NEW_LINE);
		robotCode.append("			ahead(getRobotLocation().distance(enemy));").append(GeneralVariables.NEW_LINE);
		robotCode.append("		}").append(GeneralVariables.NEW_LINE);
		robotCode.append("	}").append(GeneralVariables.DOUBLE_LINE);

		robotCode.append("	public void backDistanceToEnemy(){").append(GeneralVariables.NEW_LINE);
		robotCode.append("		if(enemy != null){").append(GeneralVariables.NEW_LINE);
		robotCode.append("			back(getRobotLocation().distance(enemy));").append(GeneralVariables.NEW_LINE);
		robotCode.append("		}").append(GeneralVariables.NEW_LINE);
		robotCode.append("	}").append(GeneralVariables.DOUBLE_LINE);

		robotCode.append("	public void turnToEnemy(){").append(GeneralVariables.NEW_LINE);
		robotCode.append("		if(enemy != null){").append(GeneralVariables.NEW_LINE);
		robotCode
				.append("	   	setTurnRightRadians(normalRelativeAngleRadians(absoluteBearingRadians(getRobotLocation(), enemy) - getHeadingRadians()));")
				.append(GeneralVariables.NEW_LINE);
		robotCode.append("		}").append(GeneralVariables.NEW_LINE);
		robotCode.append("	}").append(GeneralVariables.DOUBLE_LINE);

		robotCode.append("	public void turnGunToEnemy(){").append(GeneralVariables.NEW_LINE);
		robotCode.append("		if(enemy != null){").append(GeneralVariables.NEW_LINE);
		robotCode
				.append("	   	setTurnRightRadians(normalRelativeAngleRadians(absoluteBearingRadians(getRobotLocation(), enemy) - getGunHeadingRadians()));")
				.append(GeneralVariables.NEW_LINE);
		robotCode.append("		}").append(GeneralVariables.NEW_LINE);
		robotCode.append("	}").append(GeneralVariables.DOUBLE_LINE);

		robotCode.append("	private double absoluteBearingRadians(Point2D source, Point2D target) {").append(GeneralVariables.NEW_LINE);
		robotCode.append("	    return Math.atan2(target.getX() -").append(GeneralVariables.NEW_LINE);
		robotCode.append("	        source.getX(), target.getY() - source.getY());").append(GeneralVariables.NEW_LINE);
		robotCode.append("	}").append(GeneralVariables.NEW_LINE);

		robotCode.append("	private double normalRelativeAngleRadians(double angle) {").append(GeneralVariables.NEW_LINE);
		robotCode.append("	    return Math.atan2(Math.sin(angle), Math.cos(angle));").append(GeneralVariables.NEW_LINE);
		robotCode.append("	}").append(GeneralVariables.NEW_LINE);

		robotCode.append("	 private Point2D getRobotLocation() {").append(GeneralVariables.NEW_LINE);
		robotCode.append("	    return new Point2D.Double(getX(), getY());").append(GeneralVariables.NEW_LINE);
		robotCode.append("	}").append(GeneralVariables.NEW_LINE);

		robotCode.append("	private void goTo(Point2D point) {").append(GeneralVariables.NEW_LINE);
		robotCode.append("	   setTurnRightRadians(").append(GeneralVariables.NEW_LINE);
		robotCode.append("	       normalRelativeAngleRadians(").append(GeneralVariables.NEW_LINE);
		robotCode.append("	           absoluteBearingRadians(getRobotLocation(), point) - getHeadingRadians()")
				.append(GeneralVariables.NEW_LINE);
		robotCode.append("	        )").append(GeneralVariables.NEW_LINE);
		robotCode.append("	     );").append(GeneralVariables.NEW_LINE);
		robotCode.append("	    setAhead(getRobotLocation().distance(point));").append(GeneralVariables.NEW_LINE);
		robotCode.append("	}").append(GeneralVariables.NEW_LINE);
	}

	public static GPRobotFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new GPRobotFactory();
		}
		return INSTANCE;
	}
}
