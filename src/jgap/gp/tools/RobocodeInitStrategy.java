package jgap.gp.tools;

import org.jgap.gp.CommandGene;
import org.jgap.gp.IGPChromosome;
import org.jgap.gp.IGPInitStrategy;
import org.jgap.gp.function.SubProgram;

public class RobocodeInitStrategy implements IGPInitStrategy {

	private static final long serialVersionUID = -5781655152312445303L;

	public CommandGene init(IGPChromosome a_chrom, int a_chromNum) throws Exception {
		return new SubProgram(a_chrom.getGPConfiguration(), new Class[] { CommandGene.VoidClass, CommandGene.VoidClass }, true);
	}

}
