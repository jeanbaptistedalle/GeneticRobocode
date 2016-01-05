package com.univ.angers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author etudiant
 *
 */
public class Main {

	public static List<Robot> generateFirstGeneration(int nbRobot) {
		final RobotFactory robotFactory = RobotFactory.getInstance();
		final List<Robot> robots = new ArrayList<Robot>();
		for (int i = 0; i < nbRobot; i++) {
			final String robotName = "GenRobot_1_" + i;
			final Robot robot = robotFactory.buildTestRobot(robotName, GeneralVariables.GENERATION_ROBOT_PACKAGE);
			robots.add(robot);
		}
		return robots;
	}

	public static void main(final String[] args) {
		final GeneticRobocode geneticRobocode = GeneticRobocode.getInstance();
		if (!geneticRobocode.checkConfig()) {
			System.out.println("Abort GeneticRobocode.");
			return;
		}
		final String battleName = "battle_gen_1.battle";
		final String resultName = "battle_gen_1.txt";
		int nbRobots = 10;
		final List<Robot> robots = generateFirstGeneration(nbRobots);

		geneticRobocode.buildBattleFile(battleName, robots);
		geneticRobocode.launchRobocode(battleName, resultName);
	}
}
