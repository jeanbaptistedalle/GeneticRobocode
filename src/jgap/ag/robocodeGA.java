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

		Configuration conf = new DefaultConfiguration(); // setup GA with default config
		conf.addGeneticOperator(new MutationOperator(conf, 10)); // add new crossover opp 1/10% rate to the GA
		conf.setPreservFittestIndividual(true); // use elitsim
		conf.setFitnessFunction(this); // Set fitness function to conf

		// set up sample genes - add multiple genes to the array
		final TableauGeneInit tab1 = TableauGeneInit.getInstance();
		final TableauGeneRun tab2 = TableauGeneRun.getInstance();
		final TableauGeneOnscan tab3 = TableauGeneOnscan.getInstance();
		final TableauGeneOnhitrobot tab4 = TableauGeneOnhitrobot.getInstance();
		final TableauGeneOnhitwall tab5 = TableauGeneOnhitwall.getInstance();
		final Random rand = new Random();
		//rand_init.nextInt(tab.size());
		Gene[] sampleGenes = new Gene[CHROMOSOME_AMOUNT];
		sampleGenes[0] = new GeneInitialisation(conf, tab1.getContenuGene(rand.nextInt(tab1.size())));
		sampleGenes[1] = new GeneRun(conf, tab2.getContenuGene(rand.nextInt(tab2.size())));
		sampleGenes[2] = new GeneOnScan(conf, tab3.getContenuGene(rand.nextInt(tab3.size())));
		sampleGenes[3] = new GeneOnHitRobot(conf, tab4.getContenuGene(rand.nextInt(tab4.size())));
		sampleGenes[4] = new GeneOnHitWall(conf, tab5.getContenuGene(rand.nextInt(tab5.size())));
		/* sampleGenes[1] = new DoubleGene(conf,-200,200) */

		IChromosome sampleChromosome = new Chromosome(conf, sampleGenes); // create chromo from genes
		conf.setSampleChromosome(sampleChromosome); // set chromo to conf

		conf.setPopulationSize(POPULATION_SIZE); // create a population
		MutationOperator mo = (MutationOperator)conf.getGeneticOperators().get(1);
		//mo.setMutationRate()
		System.out.println(mo.getMutationRate());
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
		int j = 0;// nombre de type de gene.
		int i = 0;// nombre de gene du type i
		int maxBoucle = NB_GENE_INITIALIZE;
		for ( i = 0 ; i < maxBoucle ; i++) {
			if (chromo[j] == null) {
				chromo[j] = "";
			}
			chromo[j] += ((Generobocode) chromosome.getGene(i)).getAllele().getCode();
		}
		j++;
		maxBoucle+=NB_GENE_MAIN;
		for (; i < maxBoucle; i++) {
			if (chromo[j] == null) {
				chromo[j] = "";
			}
			chromo[j] += ((Generobocode) chromosome.getGene(i)).getAllele().getCode();
		}
		j++;
		maxBoucle+=NB_GENE_ON_SCAN;
		for (; i < maxBoucle; i++) {
			if (chromo[j] == null) {
				chromo[j] = "";
			}
			chromo[j] += ((Generobocode) chromosome.getGene(i)).getAllele().getCode();
		}
		j++;
		maxBoucle+=NB_GENE_ON_HITROBOT;
		for (; i < maxBoucle; i++) {
			if (chromo[j] == null) {
				chromo[j] = "";
			}		
			chromo[j] += ((Generobocode) chromosome.getGene(i)).getAllele().getCode();
			
		}
		j++;
		maxBoucle+=NB_GENE_ON_HITWALL;
		for (; i < maxBoucle; i++) {
			if (chromo[j] == null) {
				chromo[j] = "";
			}
				
			chromo[j] += ((Generobocode) chromosome.getGene(i)).getAllele().getCode();
			
		}
		j++;
		//not really pretty  
			
			/*for (int aff=0 ; aff < NB_PART_CODE ; aff++)
			{
				System.out.println(chromo[aff]);
			}*/
			
			return chromo;
			
	}

	protected double evaluate(IChromosome chromosome) {
		final String robotName = "AgRobot" + chromosome.getConfiguration().getGenerationNr();
		final String robotPackageAndName = GeneralVariables.GENERATION_ROBOT_PACKAGE + "." + robotName;
		final String preparedRobotName = robotPackageAndName+"*";
		final Robot robot = RobotFactory.getInstance().buildGenRobot(robotName, GeneralVariables.GENERATION_ROBOT_PACKAGE,
				getRobotcode(chromosome));
		final RobocodeEngine engine;
		final BattlefieldSpecification battlefield;

		engine = new RobocodeEngine(new java.io.File(""));
		engine.setVisible(false);
		engine.addBattleListener(new BattleAdaptor() {
			public void onBattleCompleted(final BattleCompletedEvent e) {
				for (final robocode.BattleResults result : e.getSortedResults()) {
					if (preparedRobotName.equals(result.getTeamLeaderName())) {
						fitness = (double) result.getScore();
					}
				}
			}
		});
		battlefield = new BattlefieldSpecification(GeneralVariables.BATTLE_WIDTH, GeneralVariables.BATTLE_HEIGHT);
		// "sample.VelociRobot,sample.RamFire,sample.Fire,sample.Crazy,"
		final String robotsName = "sample.RamFire," + preparedRobotName;
		final RobotSpecification[] selectedRobots = engine.getLocalRepository(robotsName);
		final BattleSpecification battleSpec = new BattleSpecification(GeneralVariables.NUMBER_OF_ROUND, battlefield,
				selectedRobots);
		engine.runBattle(battleSpec, true);
		engine.close();
		// robot.destroy();
		double retour = fitness;
		fitness = 0d;

		return retour > 0 ? retour : 0; // return fitness score if it's over 0
	}
}
