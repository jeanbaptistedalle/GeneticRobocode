package param.numeric.ag;

import org.jgap.Chromosome;


import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;



public class Main {

	/**
	 * @param args
	 */
	
	private static final int MAX_ALLOWED_EVOLUTIONS = 30;
	private static final int POPULATION_SIZE = 30;
	
	
	public static void main(String[] args) throws InvalidConfigurationException {
		
		/*String params = "int param_a = 8; int param_b = 8; int param_c = 8; int param_d = 8; int param_e = 8; int param_f = 8;";
		BuildRobot b = new BuildRobot();
		b.build("Test", "generation", params);*/
		
		// -----------------------------------------------------------------
	    Configuration conf = new DefaultConfiguration();
	    conf.setPreservFittestIndividual(true);
	    // -----------------------------------------------------------------
	    FitnessFunction myFunc =
	        new Fitness();
	    conf.setFitnessFunction(myFunc);
	    // -----------------------------------------------------------------
        Gene[] sampleGenes = new Gene[6];
        
        
	    sampleGenes[0] =  new MoveGene(conf, 1, 1);
	    sampleGenes[1] =  new TurnGene(conf, 1, 1);
	    sampleGenes[2] =  new MoveGene(conf, 1, 1);
	    sampleGenes[3] =  new TurnGene(conf, 1, 1);
	    sampleGenes[4] =  new FireGene(conf, 1, 1);
	    sampleGenes[5] =  new TurnGene(conf, 1, 1);
	    
   
        
	    IChromosome sampleChromosome = new Chromosome(conf, sampleGenes);
	    conf.setSampleChromosome(sampleChromosome);
	    // ------------------------------------------------------------
	    conf.setPopulationSize(POPULATION_SIZE);
	    // -----------------------------------------------------------------
	    Genotype population;
	    population = Genotype.randomInitialGenotype(conf);
	    // ---------------------------------------------------------------
	    IChromosome[] bestofgen =  new IChromosome[MAX_ALLOWED_EVOLUTIONS];
	    long startTime = System.currentTimeMillis();
	    for (int i = 0; i < MAX_ALLOWED_EVOLUTIONS; i++) {
	    		population.evolve();  
	    		bestofgen[i] = population.getFittestChromosome();
	    		System.err.println("///////////////////////////////////////////////// "+bestofgen[i].getFitnessValue());
	    }
	    long endTime = System.currentTimeMillis();
 	    System.out.println("Total evolution time: " + ( endTime - startTime)
	                       + " ms");
	    // ---------------------------------------------------------------------
	    // Display the best solution we found.
	    // --------------.---------------------
	    IChromosome bestSolutionSoFar = population.getFittestChromosome();
	    System.out.println("\n\n BEST FITNESS SCORE : "+bestSolutionSoFar.getFitnessValue());
	    System.out.println("********CHROMOSOME**************");
	    System.out.println(bestSolutionSoFar.getGene(0).getAllele().toString());
	    System.out.println(bestSolutionSoFar.getGene(1).getAllele().toString());
	    System.out.println(bestSolutionSoFar.getGene(2).getAllele().toString());
	    System.out.println(bestSolutionSoFar.getGene(3).getAllele().toString());
	    System.out.println(bestSolutionSoFar.getGene(4).getAllele().toString());
	    System.out.println(bestSolutionSoFar.getGene(5).getAllele().toString());
	    System.out.println("********************************");
		
		for (int i = 0; i < bestofgen.length; i++) {
			System.out.println(bestofgen[i].getFitnessValue());
		}
	}

}
