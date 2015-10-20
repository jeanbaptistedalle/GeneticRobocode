package jgap.command;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;

public class Ahead extends RobotCommand implements IMutateable {

	private static final long serialVersionUID = 8760293710431620964L;

	public Ahead(final GPConfiguration conf) throws InvalidConfigurationException {
		super(conf);
	}

	public CommandGene applyMutation(int index, double a_percentage) throws InvalidConfigurationException {
		Back mutant = new Back(getGPConfiguration());
		return mutant;
	}

	public String toString() {
		return "Ahead";
	}
}
