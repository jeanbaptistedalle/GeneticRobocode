package jgap.gp.fitness;

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

	public GPRobocodeFitnessFunction() {
		super();
	}

	protected double evaluate(final IGPProgram prog) {
		final RobocodeEngine engine;
		final BattlefieldSpecification battlefield;
		robotName = GeneralVariables.ROBOT_PACKAGE + ".Generobot" + prog.getGPConfiguration().getGenerationNr() + "*";
		final Robot robot = Jgap2Java.getRobotFromGP(prog, GeneralVariables.ROBOT_PACKAGE);

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
		// "sample.VelociRobot,sample.RamFire,sample.Fire,sample.Crazy,"
		final String robotsName = "sample.CirclingBot," + robotName;
		final RobotSpecification[] selectedRobots = engine.getLocalRepository(robotsName);
		final BattleSpecification battleSpec = new BattleSpecification(GeneralVariables.NUMBER_OF_ROUND, battlefield, selectedRobots);
		engine.runBattle(battleSpec, true);
		engine.close();
		robot.destroy();
		double retour = fitness;
		fitness = 0d;
		return retour > 0 ? retour : 0;
	}

}
