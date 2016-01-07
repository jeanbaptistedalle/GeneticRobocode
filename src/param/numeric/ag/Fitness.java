/*
 * This file is part of JGAP.
 *
 * JGAP offers a dual license model containing the LGPL as well as the MPL.
 *
 * For licensing information please see the file license.txt included with JGAP
 * or have a look at the top of class org.jgap.Chromosome which representatively
 * includes the JGAP license policy applicable for any file delivered with JGAP.
 */
package param.numeric.ag;






import java.util.Random;

import org.jgap.*;

import parametrage.ag.Battle;
import parametrage.ag.InjectParams;





public class Fitness
    extends FitnessFunction {

	private double fitness;
	private String[] robots = {"CirclingBot","Fire","RamFire","Tracker","TrackFire","SpinBot","Corners","Crazy","Target","Walls"}; 
	
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
											
		String name = 	"Test";						
		BuildRobot b = new BuildRobot();
		b.build(name, "generation", p);
		
		Random rand = new Random();
	  	String openent1 = "generation."+name+"*,sample."+robots[rand.nextInt(10)];
	  	String openent2 = "generation."+name+"*,sample."+robots[rand.nextInt(10)];
	  	String openent3 = "generation."+name+"*,sample."+robots[rand.nextInt(10)];
	  	
	  	
		fitness = 0;
	  	
	  	Battle battle = new Battle();
		battle.lanchBattle(openent1, "Test", false);
	
		fitness += (double)battle.getResult()/5;
		
		battle.lanchBattle(openent2, "Test", false);
		fitness += (double)battle.getResult()/5;
		
		battle.lanchBattle(openent3, "Test", false);
		fitness += (double)battle.getResult()/5;
		
		return fitness/3 ; 
}
  

}
