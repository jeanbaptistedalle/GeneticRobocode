package jgap.ag;
import org.jgap.*;
import org.jgap.impl.*;

public class AGRobocode {
	//nombre maximal d'evolution
	private static final int Max_allowed_evolution = 100;
	
	public static void makeRobot() throws Exception
	{
		Configuration conf = new DefaultConfiguration();
		
		// Add custom mutation operator
	   // conf.getGeneticOperators().clear();
//	    IUniversalRateCalculator mutCalc = new CoinsMutationRateCalc();
	    //TwoWayMutationOperator mutOp = new TwoWayMutationOperator(conf, 7);
	    //conf.addGeneticOperator(mutOp);
	    //conf.addGeneticOperator(new CrossoverOperator(conf));
	    //conf.setPreservFittestIndividual(!true);
	    //conf.setKeepPopulationSizeConstant(false);
		
		
	}

}
