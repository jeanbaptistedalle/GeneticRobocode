package jgap;

import org.jgap.InvalidConfigurationException;
import org.jgap.event.GeneticEvent;
import org.jgap.event.GeneticEventListener;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPProblem;
import org.jgap.gp.function.If;
import org.jgap.gp.function.IfElse;
import org.jgap.gp.function.Loop;
import org.jgap.gp.function.SubProgram;
import org.jgap.gp.impl.DeltaGPFitnessEvaluator;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.gp.impl.TournamentSelector;

import jgap.command.Ahead;
import jgap.command.Back;
import jgap.command.Fire;
import jgap.command.TurnGunLeft;
import jgap.command.TurnGunRight;
import jgap.command.TurnLeft;
import jgap.command.TurnRadarLeft;
import jgap.command.TurnRadarRight;
import jgap.command.TurnRight;
import jgap.fitness.TestFitnessFunction;

public class JGAPRobocode extends GPProblem {

	public JGAPRobocode(GPConfiguration conf) throws InvalidConfigurationException {
		super(conf);
	}

	public GPGenotype create() throws InvalidConfigurationException {
		Class[] types = { CommandGene.VoidClass };
		Class[][] argTypes = { {} };
		int[] minDepths = new int[] { 3 };
		int[] maxDepths = new int[] { 9 };
		GPConfiguration conf = getGPConfiguration();
		CommandGene[][] nodeSets = {
				{ new SubProgram(conf, new Class[] { CommandGene.VoidClass, CommandGene.VoidClass }, true),
						new Ahead(conf), new Back(conf), new Fire(conf), new TurnGunLeft(conf), new TurnGunRight(conf),
						new TurnRadarLeft(conf), new TurnRadarRight(conf), new TurnRight(conf), new TurnLeft(conf),
						new IfElse(conf, CommandGene.VoidClass), new If(conf, CommandGene.VoidClass),
						new Loop(conf, CommandGene.DoubleClass, 3), } };
		// TODO : Opérateur planté :
		// new Equals(conf, CommandGene.DoubleClass)
		// Create genotype with initial population.
		// ----------------------------------------
		return GPGenotype.randomInitialGenotype(conf, types, argTypes, nodeSets, minDepths, maxDepths, 1000,
				new boolean[] { true }, true);
	}

	public static void main(String[] args) {
		try {
			System.out.println("Robocode problem");
			GPConfiguration config = new GPConfiguration();
			config.setGPFitnessEvaluator(new DeltaGPFitnessEvaluator());
			int popSize = 50;
			System.out.println("Using population size of " + popSize);
			config.setSelectionMethod(new TournamentSelector());
			config.setFitnessFunction(new TestFitnessFunction());
			config.setMaxInitDepth(7);
			config.setPopulationSize(popSize);
			config.setCrossoverProb(0.9f);
			config.setReproductionProb(0.1f);
			config.setNewChromsPercent(0.3f);
			config.setStrictProgramCreation(true);
			config.setUseProgramCache(true);
			final JGAPRobocode jgapRobocode = new JGAPRobocode(config);
			GPGenotype gp = jgapRobocode.create();
			gp.setVerboseOutput(true);
			final Thread t = new Thread(gp);
			config.getEventManager().addEventListener(GeneticEvent.GPGENOTYPE_EVOLVED_EVENT,
					new GeneticEventListener() {
						public void geneticEventFired(GeneticEvent e) {
							GPGenotype genotype = (GPGenotype) e.getSource();
							int evno = genotype.getGPConfiguration().getGenerationNr();
							if (evno > 100) {
								t.stop();
							}
						}
					});
			t.start();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}
	}
}
