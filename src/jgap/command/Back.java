package jgap.command;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;

public class Back extends RobotCommand implements IMutateable {

	private static final long serialVersionUID = -5339823060712505234L;

	public Back(final GPConfiguration conf) throws InvalidConfigurationException {
		super(conf);
	}

	public CommandGene applyMutation(int index, double a_percentage) throws InvalidConfigurationException {
		Ahead mutant = new Ahead(getGPConfiguration());
		return mutant;
	}

	public String toString() {
		return "Back";
	}
}