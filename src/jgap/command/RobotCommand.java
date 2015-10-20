package jgap.command;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.impl.GPConfiguration;

public abstract class RobotCommand extends CommandGene {

	private static final long serialVersionUID = -3914122395860243844L;

	public RobotCommand(final GPConfiguration conf) throws InvalidConfigurationException {
		super(conf, 0, CommandGene.VoidClass);
	}

	public RobotCommand(final GPConfiguration conf, int arity, Class a_type) throws InvalidConfigurationException {
		super(conf, arity, a_type);
	}
}
