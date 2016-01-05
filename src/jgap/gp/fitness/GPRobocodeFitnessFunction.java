package jgap.gp.fitness;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;

import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPProgram;

import com.univ.angers.GeneralVariables;
import com.univ.angers.Robot;

import jgap.gp.tools.Jgap2Java;
import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSpecification;
import robocode.control.events.BattleAdaptor;
import robocode.control.events.BattleCompletedEvent;

public class GPRobocodeFitnessFunction extends GPFitnessFunction {

	private static final long serialVersionUID = -6519986147765184021L;

	private Double fitness;
	private String robotName;
	private List<String> testRobotsName;

	public GPRobocodeFitnessFunction(final List<String> testRobotsName) throws FileNotFoundException {
		super();
		this.testRobotsName = testRobotsName;
	}

	protected double evaluate(final IGPProgram prog) {
		final RobocodeEngine engine;
		final BattlefieldSpecification battlefield;
		robotName = GeneralVariables.GENERATION_ROBOT_PACKAGE + ".Generobot" + prog.getGPConfiguration().getGenerationNr() + "*";
		final Robot robot = Jgap2Java.getRobotFromGP(prog, GeneralVariables.GENERATION_ROBOT_PACKAGE);

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

		
		double retour = 0;
		fitness=0d;
		for (final String testRobotName : testRobotsName) {
			final Random rand = new Random();
			// Parmi les robots disponible, on en selectionne un au hasard
			// contre qui le robot généré se battra
			final String robotsName = testRobotName + "," + robotName;
			final RobotSpecification[] selectedRobots = engine.getLocalRepository(robotsName);
			final BattleSpecification battleSpec = new BattleSpecification(GeneralVariables.GP_NUMBER_OF_ROUND, battlefield, selectedRobots);
			try {
				engine.runBattle(battleSpec, true);
			} catch (final NullPointerException e) {
				//Do nothing
			}
			retour += fitness;
			fitness = 0d;
		}
		engine.close();
		robot.clean();
		return retour > 0 ? retour : 0;
	}

}
