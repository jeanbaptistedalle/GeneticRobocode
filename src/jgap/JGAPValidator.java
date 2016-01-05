package jgap;

import java.util.ArrayList;

import com.univ.angers.GeneralVariables;

import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSpecification;
import robocode.control.events.BattleAdaptor;
import robocode.control.events.BattleCompletedEvent;

public class JGAPValidator {

	private double fitness = 0;

	public void validate() {

		final RobocodeEngine engine;
		final BattlefieldSpecification battlefield;
		double score = 0;

		//Modifier ici le nom du robot à tester
		String robotShortName = "Generobot1";
		
		final String robotName = GeneralVariables.GENERATION_ROBOT_PACKAGE + "."+robotShortName+"*";

		engine = new RobocodeEngine(new java.io.File(""));
		engine.setVisible(false);
		engine.addBattleListener(new BattleAdaptor() {
			public void onBattleCompleted(final BattleCompletedEvent e) {
				for (final robocode.BattleResults result : e.getSortedResults()) {
					if (robotName.equals(result.getTeamLeaderName())) {
						fitness = (double) result.getScore();
					}
				}
			}
		});
		battlefield = new BattlefieldSpecification(GeneralVariables.BATTLE_WIDTH, GeneralVariables.BATTLE_HEIGHT);

		final String[] robots = GeneralVariables.getTestRobotsName().toArray(new String[0]);
		fitness = 0d;
		for (final String testRobotName : robots) {
			final String robotsName = testRobotName+","+robotName; 
			final RobotSpecification[] selectedRobots = engine.getLocalRepository(robotsName);
			final BattleSpecification battleSpec = new BattleSpecification(GeneralVariables.GP_NUMBER_OF_ROUND, battlefield,
					selectedRobots);
			try {
				engine.runBattle(battleSpec, true);
			} catch (final NullPointerException e) {
				// Do nothing
			}
			score += fitness;
			fitness = 0d;
		}
		engine.close();
		System.out.println("Score du robot "+robotShortName+" à la validation : " + score);
	}

	public static void main(final String[] args) {
		final JGAPValidator validator = new JGAPValidator();
		validator.validate();
	}
}
