package jgap.gp.selector;

import java.io.Serializable;

import org.jgap.gp.IGPProgram;
import org.jgap.gp.INaturalGPSelector;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.gp.impl.GPPopulation;

public class BestSelector implements INaturalGPSelector, Serializable, Cloneable {

	private static final long serialVersionUID = -6827007792393040398L;

	@Override
	public IGPProgram select(GPGenotype a_genotype) {
		GPPopulation pop = a_genotype.getGPPopulation();
		IGPProgram bestProgram = null;
		int popSize = pop.getPopSize();
		for (int i = 0; i < popSize; i++) {
			if (bestProgram == null) {
				bestProgram = pop.getGPProgram(i);
			} else {
				IGPProgram prog = pop.getGPProgram(i);
				if (prog.getFitnessValue() > bestProgram.getFitnessValue()) {
					bestProgram = prog;
				}
			}
		}
		return bestProgram;
	}

}
