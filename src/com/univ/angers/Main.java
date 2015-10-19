package com.univ.angers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

	public static List<Robot> generateFirstGeneration(int nbRobot) {
		final RobotFactory robotFactory = RobotFactory.getInstance();
		final List<Robot> robots = new ArrayList<Robot>();
		for (int i = 0; i < nbRobot; i++) {
			final String robotName = "GenRobot_1_" + i;
			final Robot robot = robotFactory.buildTestRobot(robotName, GeneralVariables.ROBOT_PACKAGE);
			robot.makeRobot();
			robots.add(robot);
		}
		return robots;
	}

	public static List<Robot> generateNextGeneration(List<Robot> robots, int nbRobot, int noGeneration) {
		final RobotFactory robotFactory = RobotFactory.getInstance();
		final Random rand = new Random();
		final List<Robot> newRobots = new ArrayList<Robot>();
		for (int i = 0; i < nbRobot; i++) {
			final String robotName = "GenRobot_" + noGeneration + "_" + i;

			final Robot mother = robots.get(rand.nextInt(robots.size()));
			final Robot father = robots.get(rand.nextInt(robots.size()));
			final Robot robot = robotFactory.buildNextGenRobot(robotName, GeneralVariables.ROBOT_PACKAGE, mother, father);
			robot.makeRobot();
			newRobots.add(robot);
		}
		for (Robot robot : newRobots) {
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
