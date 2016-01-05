package parametrage.ag;





import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;




public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	
	private static final int MAX_ALLOWED_EVOLUTIONS = 50;
	private static final int POPULATION_SIZE = 30;
	
	
	public static void main(String[] args) throws IOException, InvalidConfigurationException {
		
	/*	

		
	*/
		
		// -----------------------------------------------------------------
	    Configuration conf = new DefaultConfiguration();
	    conf.setPreservFittestIndividual(true);
	    // -----------------------------------------------------------------
	    FitnessFunction myFunc =
	        new Fitness();
	    conf.setFitnessFunction(myFunc);
	    // -----------------------------------------------------------------
        Gene[] sampleGenes = new Gene[6];
        
        
	    sampleGenes[0] =  new IntegerGene(conf, 1, 360);
	    sampleGenes[1] =  new IntegerGene(conf, 1, 360);
	    sampleGenes[2] =  new IntegerGene(conf, 1, 360);
	    sampleGenes[3] =  new IntegerGene(conf, 1, 360);
	    sampleGenes[4] =  new IntegerGene(conf, 1, 360);
	    sampleGenes[5] =  new IntegerGene(conf, 1, 360);
	    
   
        
	    IChromosome sampleChromosome = new Chromosome(conf, sampleGenes);
	    conf.setSampleChromosome(sampleChromosome);
	    // ------------------------------------------------------------
	    conf.setPopulationSize(POPULATION_SIZE);
	    // -----------------------------------------------------------------
	    Genotype population;
	    population = Genotype.randomInitialGenotype(conf);
	    // ---------------------------------------------------------------
	    long startTime = System.currentTimeMillis();
	    for (int i = 0; i < MAX_ALLOWED_EVOLUTIONS; i++) {
	    		population.evolve();  
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
		
		
	}
	
	
	

	


}
