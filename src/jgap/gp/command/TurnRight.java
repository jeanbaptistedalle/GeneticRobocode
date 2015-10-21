package jgap.gp.command;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;

public class TurnRight extends RobotCommand implements IMutateable {

	private static final long serialVersionUID = -3988795906815130437L;

	public TurnRight(final GPConfiguration conf) throws InvalidConfigurationException {
		super(conf);
	}

	public CommandGene applyMutation(int index, double a_percentage) throws InvalidConfigurationException {
		TurnLeft mutant = new TurnLeft(getGPConfiguration());
		return mutant;
	}

	public String toString() {
		return "TurnRight";
	}
}
