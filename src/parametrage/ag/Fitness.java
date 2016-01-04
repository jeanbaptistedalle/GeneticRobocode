/*
 * This file is part of JGAP.
 *
 * JGAP offers a dual license model containing the LGPL as well as the MPL.
 *
 * For licensing information please see the file license.txt included with JGAP
 * or have a look at the top of class org.jgap.Chromosome which representatively
 * includes the JGAP license policy applicable for any file delivered with JGAP.
 */
package parametrage.ag;






import org.jgap.*;




public class Fitness
    extends FitnessFunction {

	private double fitness;
	
  public Fitness() {
	  
  }

  
  public double evaluate(IChromosome a_subject) {
	 
	  	String[] params = new String[6];
	  	for (int i = 0; i < params.length; i++) {
	  		params[i] = a_subject.getGene(i).getAllele().toString();
		}
	  	
		
		String p = "";
		
		p += "int param_a = " + params[0];
			p += "; int param_b = " + params[1];
					p += "; int param_c = " + params[2];
							p += "; int param_d = " + params[3];
									p +=  "; int param_e = " + params[4];
											p += "; int param_f = " + params[5]+ ";";
											
												
											
	  	InjectParams inject = new InjectParams("Test","generation",p);
	  	inject.readRobocode();
		inject.writeRobocode();
		
	  	
		Battle battle = new Battle();
		battle.lanchBattle("generation.Test*,sample.Corners", "Test", false);
		fitness += (double)battle.getResult();
		
		battle.lanchBattle("generation.Test*,sample.RamFire", "Test", false);
		fitness += (double)battle.getResult();
		
		battle.lanchBattle("generation.Test*,sample.Fire", "Test", false);
		fitness += (double)battle.getResult();
		
		return fitness/3 ; 
  }
  

}
