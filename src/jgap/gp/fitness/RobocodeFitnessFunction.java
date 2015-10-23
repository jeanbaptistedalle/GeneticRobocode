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

public class RobocodeFitnessFunction extends GPFitnessFunction {

	private static final long serialVersionUID = -6519986147765184021L;

	private Double fitness;
	private String robotName;

	public RobocodeFitnessFunction() {
		super();
	}

	protected double evaluate(final IGPProgram prog) {
//		boolean fire = false;
//		boolean movement = false;
//		for (int i = 0; i < GeneralVariables.NUMBER_OF_CHROMOSOME; i++) {
//			final ProgramChromosome chrom = prog.getChromosome(i);
//			for (final CommandGene command : chrom.getFunctions()) {
//				if (command != null) {
//					final Class<? extends CommandGene> clazz = command.getClass();
//					if (clazz == Fire.class) {
//						fire = true;
//					} else if (clazz == Ahead.class || clazz == Back.class) {
//						movement = true;
//					}
//				}
//			}
//		}
//		if (!fire || !movement) {
//			return 0;
//		}
		final RobocodeEngine engine;
		final BattlefieldSpecification battlefield;
		robotName = GeneralVariables.ROBOT_PACKAGE + ".Generobot" + prog.getGPConfiguration().getGenerationNr() + "*";
		final Robot robot = Jgap2Java.getRobotFromChrom(prog, GeneralVariables.ROBOT_PACKAGE);

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
		final String robotsName = "sample.TrackFire," + robotName;
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
