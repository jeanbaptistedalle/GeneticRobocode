	package jgap.gp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jgap.InvalidConfigurationException;
import org.jgap.event.GeneticEvent;
import org.jgap.event.GeneticEventListener;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPProblem;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.gp.impl.TournamentSelector;
import org.jgap.util.NumberKit;
import org.jgap.util.SystemKit;

import com.univ.angers.GeneralVariables;

import jgap.gp.fitness.GPRobocodeFitnessFunction;
import jgap.gp.tools.Configurations;
import jgap.gp.tools.Jgap2Java;
import jgap.gp.tools.RobocodeInitStrategy;

public class JGAPRobocode extends GPProblem {

	double bestFit;

	public JGAPRobocode(final GPConfiguration conf) throws InvalidConfigurationException {
		super(conf);
		bestFit = 0;
	}

	public GPGenotype create() throws InvalidConfigurationException {
		final GPConfiguration conf = getGPConfiguration();
		final CommandGene[] avalaibleCommand = Configurations.getConfigurationAvancee(conf);
		final CommandGene[][] nodeSets = new CommandGene[GeneralVariables.GP_NUMBER_OF_BLOCS][];
		final Class<?>[] types = new Class[GeneralVariables.GP_NUMBER_OF_BLOCS];
		final Class<?>[][] argTypes = new Class[GeneralVariables.GP_NUMBER_OF_BLOCS][];
		int[] minDepths = new int[GeneralVariables.GP_NUMBER_OF_BLOCS];
		int[] maxDepths = new int[GeneralVariables.GP_NUMBER_OF_BLOCS];
		boolean[] fullModeAllowed = new boolean[GeneralVariables.GP_NUMBER_OF_BLOCS];
		for (int i = 0; i < GeneralVariables.GP_NUMBER_OF_BLOCS; i++) {
			nodeSets[i] = avalaibleCommand;
			types[i] = CommandGene.VoidClass;
			argTypes[i] = new Class[0];
			minDepths[i] = 1;
			maxDepths[i] = 10;
			fullModeAllowed[i] = true;
		}
		int maxNode = 5000;
		// Create genotype with initial population.
		// ----------------------------------------
		return GPGenotype.randomInitialGenotype(conf, types, argTypes, nodeSets, minDepths, maxDepths, maxNode, fullModeAllowed, true);
	}
	
	public static List<String> selectTestRobots(int numberOfRobotSelected){
		final Random rand = new Random();
		final List<String> robots = GeneralVariables.getTestRobotsName();
		final List<String> selectedRobots = new ArrayList<String>();
		for(int i = 0; i < numberOfRobotSelected; i++){
			selectedRobots.add(robots.remove(rand.nextInt(robots.size())));
		}
		return selectedRobots;
	}

	public static void main(final String[] args) {
		try {
			final File bestRobotFolder = new File(GeneralVariables.ROBOTS_FOLDER + "/" + GeneralVariables.BEST_ROBOT_PACKAGE);
			for (final File child : bestRobotFolder.listFiles()) {
				child.delete();
			}
			final File generationRobotFolder = new File(GeneralVariables.ROBOTS_FOLDER + "/" + GeneralVariables.GENERATION_ROBOT_PACKAGE);
			for (final File child : generationRobotFolder.listFiles()) {
				child.delete();
			}
			System.out.println("Robocode problem");
			final GPConfiguration config = new GPConfiguration();
			System.out.println("Using population size of " + GeneralVariables.GP_POPULATION_SIZE);
			config.setSelectionMethod(new TournamentSelector());
			final List<String> selectedTestRobots = JGAPRobocode.selectTestRobots(GeneralVariables.GP_NUMBER_OF_TEST_ROBOT);
			System.out.println("Test robots : "+selectedTestRobots);
			try {
				config.setFitnessFunction(new GPRobocodeFitnessFunction(selectedTestRobots));
			} catch (final FileNotFoundException e) {
				/*
				 * Il est possible que les droits ne soient pas accordé pour les
				 * logs de la classe GPFitnessFunction
				 */
			}
			config.setInitStrategy(new RobocodeInitStrategy());
			config.setMaxInitDepth(10);
			config.setPopulationSize(GeneralVariables.GP_POPULATION_SIZE);
			config.setCrossoverProb(0.8f);
			config.setReproductionProb(0.1f);
			config.setMutationProb(0.05f);
			// A chaque génération, il y a 10% de chance qu'un nouveau
			// chromosome
			// soit créé afin d'éviter les problèmes d'extremums locaux.
			config.setNewChromsPercent(0.2f);
			config.setStrictProgramCreation(false);
			// Le calcul de la fitness n'étant pas constant, il est nécessaire
			// de toujours recalculer afin de tester l'évolution contre d'autres
			// robots parmi les 10
			config.setAlwaysCaculateFitness(true);
			config.setUseProgramCache(true);
			final JGAPRobocode jgapRobocode = new JGAPRobocode(config);
			final GPGenotype gp = jgapRobocode.create();
			gp.setVerboseOutput(false);
			final Thread t = new Thread(gp);
			config.getEventManager().addEventListener(GeneticEvent.GPGENOTYPE_NEW_BEST_SOLUTION, new GeneticEventListener() {
				public void geneticEventFired(final GeneticEvent e) {
					/*
					 * Lorsqu'un robot meilleur que tous les existants est
					 * généré, on le sauvegarde en conservant son numéro. Les
					 * précédents meilleurs robots sont cependant conservé afin
					 * de garder une trace de l'évolution obtenue.
					 */
					final GPGenotype genotype = (GPGenotype) e.getSource();
					final IGPProgram prog = genotype.getFittestProgramComputed();
					Jgap2Java.getRobotFromGP(prog, GeneralVariables.BEST_ROBOT_PACKAGE, prog.getGPConfiguration().getGenerationNr() - 1);
					jgapRobocode.bestFit = prog.getFitnessValue();
					System.out.println("Better solution found !");
					System.out.println("	Solution fitness : " + NumberKit.niceDecimalNumber(jgapRobocode.bestFit, 2));
					System.out.println("	Solution : " + prog.toStringNorm(0));
				}
			});
			config.getEventManager().addEventListener(GeneticEvent.GPGENOTYPE_EVOLVED_EVENT, new GeneticEventListener() {
				@SuppressWarnings("deprecation")
				public void geneticEventFired(final GeneticEvent e) {
					// A chaque génération, on rappelle la fitness atteinte
					final GPGenotype genotype = (GPGenotype) e.getSource();
					int evno = genotype.getGPConfiguration().getGenerationNr() - 1;
					double freeMem = SystemKit.getFreeMemoryMB();
					if (evno > 0) {
						System.out.println("Generation " + evno + " has been created.");
						System.out.println("	Best fitness value ever : " + jgapRobocode.bestFit + ".");
						System.out.println("	Solutioon fitness average : " + genotype.getTotalFitness() / GeneralVariables.GP_POPULATION_SIZE + ".");
					}
					if (evno > GeneralVariables.NUMBER_OF_EVOLUTION) {
						t.stop();
					} else {
						try {
							// Collect garbage if memory low.
							// ------------------------------
							if (freeMem < 50) {
								System.gc();
								Thread.sleep(500);
							} else {
								// Avoid 100% CPU load.
								// --------------------
								Thread.sleep(30);
							}
						} catch (final InterruptedException iex) {
							iex.printStackTrace();
							System.exit(1);
						}
					}
				}
			});
			t.start();
		} catch (final Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}
	}
}
