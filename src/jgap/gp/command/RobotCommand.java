package jgap.gp.command;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.impl.GPConfiguration;

public abstract class RobotCommand extends CommandGene {

	private static final long serialVersionUID = -3914122395860243844L;

	public RobotCommand(final GPConfiguration conf) throws InvalidConfigurationException {
		super(conf, 0, CommandGene.VoidClass);
	}

	public RobotCommand(final GPConfiguration conf, int arity, Class<?> returnType) throws InvalidConfigurationException {
		super(conf, arity, returnType);
	}

	/**
	 * Fournit une String formatée dans laquelle on pourra injecter les
	 * paramètres. Les injections se font aux endroits repéré par un chiffre
	 * entre accolades
	 * 
	 * @return
	 */
	public abstract String toFormattedString();
}
