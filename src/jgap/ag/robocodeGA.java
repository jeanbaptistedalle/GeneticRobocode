package jgap.ag;

import java.util.Random;

import org.jgap.*;
import org.jgap.impl.*;

import com.univ.angers.GeneralVariables;
import com.univ.angers.Robot;
import com.univ.angers.RobotFactory;

import jgap.gp.tools.Jgap2Java;
import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSpecification;
import robocode.control.events.BattleAdaptor;
import robocode.control.events.BattleCompletedEvent;

/* 
 * Sam Ternent
 * RoboCodeGATemplate
 * Simple config for starting a project with RoboCode and JGAP
 */

@SuppressWarnings("serial")
public class robocodeGA extends FitnessFunction {

	// set amount of generations to evolve
	public static final int MAX_GENERATIONS = 10;

	// set population size per generation
	public static final int POPULATION_SIZE = 10;
	// amount of chromosomes
	public static final int CHROMOSOME_AMOUNT = 5;

	public static final int NB_PART_CODE = 5;
	
	public static final int NB_GENE_MAIN = 1;
	public static final int NB_GENE_ON_SCAN = 1;
	public static final int NB_GENE_ON_HITROBOT = 1;
	public static final int NB_GENE_ON_HITWALL = 1;
	public static final int NB_GENE_INITIALIZE = 1;
	

	double fitness;

	public void run() throws Exception {

		Configuration conf = new DefaultConfiguration(); // setup GA with
															// default config
		conf.addGeneticOperator(new MutationOperator(conf, 10)); // add new
																	// crossover
																	// opp 1/10%
																	// rate to
																	// the GA
		conf.setPreservFittestIndividual(true); // use elitsim
		conf.setFitnessFunction(this); // Set fitness function to conf

		// set up sample genes - add multiple genes to the array
		final TableauContenuGene tab = TableauContenuGene.getInstance();
		Random rand_int = new Random();
		Random rand_run = new Random();
		Random rand_onscan = new Random();
		Random rand_onhitRobot = new Random();
		Random rand_onhitWalls = new Random();
		//rand_init.nextInt(tab.size());
		Generobocode[] sampleGenes = new Generobocode[CHROMOSOME_AMOUNT];
		sampleGenes[0] = new GeneInitialisation(conf, tab.getContenuGene(/*rand_int.nextInt(7 - 0)*/rand_int.nextInt(tab.size())));
		sampleGenes[1] = new GeneRun(conf, tab.getContenuGene(/*rand_run.nextInt((14 - 7)+1) + 7*/rand_run.nextInt(tab.size())));
		sampleGenes[2] = new GeneOnScan(conf, tab.getContenuGene(/*rand_onscan.nextInt((21-15)+1)+15*/rand_onscan.nextInt(tab.size())));
		sampleGenes[3] = new GeneOnHitRobot(conf, tab.getContenuGene(/*rand_onhitRobot.nextInt((27-22)+1)+22*/rand_onhitRobot.nextInt(tab.size())));
		sampleGenes[4] = new GeneOnHitWall(conf, tab.getContenuGene(/*rand_onhitWalls.nextInt((29-27)+1)+27*/rand_onhitWalls.nextInt(tab.size())));
		/* sampleGenes[1] = new DoubleGene(conf,-200,200) */

		IChromosome sampleChromosome = new Chromosome(conf, sampleGenes); // create
																			// chromo
																			// from
																			// genes
		conf.setSampleChromosome(sampleChromosome); // set chromo to conf

		conf.setPopulationSize(POPULATION_SIZE); // create a population

		// set random population
		Genotype population = Genotype.randomInitialGenotype(conf);
		IChromosome fittestSolution = null;

		// evolve population
		for (int gen = 0; gen < MAX_GENERATIONS; gen++) {
			population.evolve(); // evolve population
			fittestSolution = population.getFittestChromosome(); // find fittest
																	// of
																	// population
			System.out.printf("\nafter %d generations the best solution is %s \n", gen + 1, fittestSolution);
		}

		// buildRobot(fittestSolution); // pass best solution to build
		System.exit(0); // clean exit
	}

	public static void main(String[] args) throws Exception {
		new robocodeGA().run(); // run main
	}

	private String[] getRobotcode(IChromosome chromosome) {
		// break down chromosome to array
		String[] chromo = new String[NB_PART_CODE];
		int j = 0;
		int x =0;	
		for (int i = 0 ; i < NB_GENE_INITIALIZE ; i++) {
			if (chromo[j] == null) {
				chromo[j] = "";
			}
			chromo[j] += ((Generobocode) chromosome.getGene(x+i)).getAllele().getCode();
			x++;
		}
		j++;
		for (int i = 0; i < NB_GENE_MAIN; i++) {
			if (chromo[j] == null) {
				chromo[j] = "";
			}
			chromo[j] += ((Generobocode) chromosome.getGene(x+i)).getAllele().getCode();
			x++;
		}
		j++;
		for (int i = 0; i < NB_GENE_ON_SCAN; i++) {
			if (chromo[j] == null) {
				chromo[j] = "";
			}
			chromo[j] += ((Generobocode) chromosome.getGene(x+i)).getAllele().getCode();
			x++;
		}
		j++;
		
		for (int i = 0; i < NB_GENE_ON_HITROBOT; i++) {
			if (chromo[j] == null) {
				chromo[j] = "";
			}
			chromo[j] += ((Generobocode) chromosome.getGene(x+i)).getAllele().getCode();
			x++;
		}
		j++;
		
		for (int i = 0; i < NB_GENE_ON_HITWALL; i++) {
			if (chromo[j] == null) {
				chromo[j] = "";
			}
			chromo[j] += ((Generobocode) chromosome.getGene(x+i)).getAllele().getCode();
			x++;
		}
		j++;
		//not really pretty  
			
			
	
			return chromo;
	}

	protected double evaluate(IChromosome chromosome) {
		final String robotName = "AgRobot" + chromosome.getConfiguration().getGenerationNr();
		final String robotPackageAndName = GeneralVariables.ROBOT_PACKAGE + "." + robotName;
		final Robot robot = RobotFactory.getInstance().buildGenRobot(robotName, GeneralVariables.ROBOT_PACKAGE,
				getRobotcode(chromosome));
		final RobocodeEngine engine;
		final BattlefieldSpecification battlefield;

		engine = new RobocodeEngine(new java.io.File(""));
		engine.setVisible(false);
		engine.addBattleListener(new BattleAdaptor() {
			public void onBattleCompleted(final BattleCompletedEvent e) {
				for (final robocode.BattleResults result : e.getSortedResults()) {
					if (robotPackageAndName.equals(result.getTeamLeaderName())) {
						fitness = (double) result.getScore();
					}
				}
			}
		});
		battlefield = new BattlefieldSpecification(GeneralVariables.BATTLE_WIDTH, GeneralVariables.BATTLE_HEIGHT);
		// "sample.VelociRobot,sample.RamFire,sample.Fire,sample.Crazy,"
		final String robotsName = "sample.TrackFire," + robotName;
		final RobotSpecification[] selectedRobots = engine.getLocalRepository(robotsName);
		final BattleSpecification battleSpec = new BattleSpecification(GeneralVariables.NUMBER_OF_ROUND, battlefield,
				selectedRobots);
		engine.runBattle(battleSpec, true);
		engine.close();
		// robot.destroy();
		double retour = fitness;
		fitness = 0d;

		return fitness > 0 ? fitness : 0; // return fitness score if it's over 0
	}
}
