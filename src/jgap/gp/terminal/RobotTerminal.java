package jgap.gp.terminal;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.terminal.Terminal;

public abstract class RobotTerminal extends Terminal{

	private static final long serialVersionUID = 2956601770149168732L;

	public RobotTerminal(GPConfiguration a_conf, Class<?> a_returnType) throws InvalidConfigurationException {
		super(a_conf, a_returnType);
	}
	
	public abstract String toFormattedString();

}
