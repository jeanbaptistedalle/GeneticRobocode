package jgap.gp.fitness;

import org.jgap.gp.CommandGene;
import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.impl.ProgramChromosome;
import org.jgap.gp.terminal.NOP;

import jgap.gp.command.RobotCommand;

public class TestFitnessFunction extends GPFitnessFunction {

	private static final long serialVersionUID = -6519986147765184021L;

	protected double evaluate(final IGPProgram prog) {
		final ProgramChromosome chrom = prog.getChromosome(0);
		final CommandGene[] tab = chrom.getFunctions();
		double cpt = 1000;
		for (final CommandGene cg : tab) {
			if (cg != null) {
				if (cg.getClass() == RobotCommand.class) {
					cpt += 10;
				} else if (cg.getClass() == NOP.class) {
					cpt -= 10;
				} else {
					cpt++;
				}
			}
		}
		if (cpt < 0) {
			return 0;
		}
		return cpt;
	}

}
