package jgap.gp.fitness;

import java.util.Random;

import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPProgram;

public class TestFitnessFunction extends GPFitnessFunction {

	private static final long serialVersionUID = -6519986147765184021L;

	@Override
	protected double evaluate(IGPProgram arg0) {
		final Random rand = new Random();
		return rand.nextDouble();
	}

}
