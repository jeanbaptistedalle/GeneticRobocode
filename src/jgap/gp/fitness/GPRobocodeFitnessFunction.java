package jgap.gp.fitness;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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

	public GPRobocodeFitnessFunction() throws FileNotFoundException {
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

		final String[] robots = new ArrayList<String>() {
			private static final long serialVersionUID = -8803657930774508702L;

			{
				add("sample.CirclingBot");
				add("sample.Corners");
				add("sample.Crazy");
				add("sample.Fire");
				add("sample.RamFire");
				add("sample.SpinBot");
				add("sample.Tracker");
				add("sample.TrackFire");
				add("sample.VelociRobot");
				add("sample.Walls");
			}
		}.toArray(new String[0]);
		double retour = 0;
		fitness=0d;
		for (int i = 0; i < GeneralVariables.NUMBER_OF_ROUND; i++) {
			final Random rand = new Random();
			// Parmi les robots disponible, on en selectionne un au hasard
			// contre qui le robot généré se battra
			final String robotsName = robots[rand.nextInt(robots.length)] + "," + robotName;
			final RobotSpecification[] selectedRobots = engine.getLocalRepository(robotsName);
			final BattleSpecification battleSpec = new BattleSpecification(GeneralVariables.NUMBER_OF_ROUND, battlefield, selectedRobots);
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
