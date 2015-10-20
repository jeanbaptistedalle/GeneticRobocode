package jgap.command;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.impl.GPConfiguration;

public class TurnLeft extends RobotCommand {

	private static final long serialVersionUID = 7153907965672557033L;

	public TurnLeft(final GPConfiguration conf) throws InvalidConfigurationException {
		super(conf);
	}

	public CommandGene applyMutation(int index, double a_percentage) throws InvalidConfigurationException {
		TurnRight mutant = new TurnRight(getGPConfiguration());
		return mutant;
	}

	public String toString() {
		return "TurnLeft";
	}
}
