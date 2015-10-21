package jgap.gp;

import org.jgap.InvalidConfigurationException;
import org.jgap.event.GeneticEvent;
import org.jgap.event.GeneticEventListener;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPProblem;
import org.jgap.gp.function.Add;
import org.jgap.gp.function.And;
import org.jgap.gp.function.Divide;
import org.jgap.gp.function.Equals;
import org.jgap.gp.function.GreaterThan;
import org.jgap.gp.function.Multiply;
import org.jgap.gp.function.Or;
import org.jgap.gp.function.SubProgram;
import org.jgap.gp.function.Subtract;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.gp.impl.TournamentSelector;
import org.jgap.gp.terminal.Terminal;
import org.jgap.util.SystemKit;

import jgap.gp.command.Ahead;
import jgap.gp.command.Back;
import jgap.gp.command.Fire;
import jgap.gp.command.IfThen;
import jgap.gp.command.IfThenElse;
import jgap.gp.command.TurnGunLeft;
import jgap.gp.command.TurnGunRight;
import jgap.gp.command.TurnLeft;
import jgap.gp.command.TurnRadarLeft;
import jgap.gp.command.TurnRadarRight;
import jgap.gp.command.TurnRight;
import jgap.gp.fitness.TestFitnessFunction;
import jgap.gp.terminal.GetEnergy;
import jgap.gp.terminal.GetGunHeading;
import jgap.gp.terminal.GetHeading;
import jgap.gp.terminal.GetRadarHeading;
import jgap.gp.terminal.GetVelocity;
import jgap.gp.terminal.GetX;
import jgap.gp.terminal.GetY;

public class JGAPRobocode extends GPProblem {

	public JGAPRobocode(GPConfiguration conf) throws InvalidConfigurationException {
		super(conf);
	}

	public GPGenotype create() throws InvalidConfigurationException {
		Class[] types = { CommandGene.VoidClass };
		Class[][] argTypes = { {} };
		int[] minDepths = new int[] { 1 };
		int[] maxDepths = new int[] { 2 };
		boolean[] fullModeAllowed = new boolean[] { true };
		GPConfiguration conf = getGPConfiguration();
		CommandGene[][] nodeSets = { {
				// Existing terminals
				new Terminal(conf, CommandGene.DoubleClass, 0d, 1000d, true),
				// Custom terminals
				new GetX(conf), new GetY(conf), new GetEnergy(conf), new GetGunHeading(conf), new GetHeading(conf),
				new GetRadarHeading(conf), new GetVelocity(conf),
				// Existing commands
				new Add(conf, CommandGene.DoubleClass), new Subtract(conf, CommandGene.DoubleClass),
				new Multiply(conf, CommandGene.DoubleClass), new Divide(conf, CommandGene.DoubleClass),
				new GreaterThan(conf, CommandGene.DoubleClass), new Or(conf), new And(conf),
				new Equals(conf, CommandGene.DoubleClass), new Equals(conf, CommandGene.DoubleClass),
				new SubProgram(conf, new Class[] { CommandGene.VoidClass, CommandGene.VoidClass }, true),
				new SubProgram(conf, new Class[] { CommandGene.VoidClass, CommandGene.VoidClass, CommandGene.VoidClass }, true),
				new SubProgram(conf, new Class[] { CommandGene.VoidClass, CommandGene.VoidClass, CommandGene.VoidClass, CommandGene.VoidClass }, true),
				new SubProgram(conf, new Class[] { CommandGene.VoidClass, CommandGene.VoidClass, CommandGene.VoidClass, CommandGene.VoidClass, CommandGene.VoidClass }, true),
				// Custom commands
				new Back(conf, CommandGene.DoubleClass), new Ahead(conf, CommandGene.DoubleClass),
				new IfThenElse(conf, CommandGene.DoubleClass), new IfThenElse(conf, CommandGene.BooleanClass),
				new IfThen(conf, CommandGene.DoubleClass), new IfThen(conf, CommandGene.BooleanClass),
				new Fire(conf, CommandGene.DoubleClass), new TurnGunLeft(conf, CommandGene.DoubleClass),
				new TurnGunRight(conf, CommandGene.DoubleClass), new TurnRadarLeft(conf, CommandGene.DoubleClass),
				new TurnRadarRight(conf, CommandGene.DoubleClass), new TurnRight(conf, CommandGene.DoubleClass),
				new TurnLeft(conf, CommandGene.DoubleClass), } };
				// FIXME : Interessant ou non ? new Loop(conf,
				// CommandGene.DoubleClass, 3),

		// Create genotype with initial population.
		// ----------------------------------------
		return GPGenotype.randomInitialGenotype(conf, types, argTypes, nodeSets, minDepths, maxDepths, 1000,
				fullModeAllowed, true);
	}

	public static void main(String[] args) {
		try {
			System.out.println("Robocode problem");
			GPConfiguration config = new GPConfiguration();
			int popSize = 50;
			System.out.println("Using population size of " + popSize);
			config.setSelectionMethod(new TournamentSelector());
			config.setFitnessFunction(new TestFitnessFunction());
			config.setMaxInitDepth(7);
			config.setPopulationSize(popSize);
			config.setCrossoverProb(0.9f);
			config.setReproductionProb(0.1f);
			config.setNewChromsPercent(0.3f);
			config.setStrictProgramCreation(false);
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
							double freeMem = SystemKit.getFreeMemoryMB();
							if (evno > 3000) {
								t.stop();
							} else {
								try {
									// Collect garbage if memory low.
									// ------------------------------
									if (freeMem < 50) {
										System.gc();
										t.sleep(500);
									} else {
										// Avoid 100% CPU load.
										// --------------------
										t.sleep(30);
									}
								} catch (InterruptedException iex) {
									iex.printStackTrace();
									System.exit(1);
								}
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
