package jgap.gp.terminal;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.terminal.Terminal;

public abstract class RobotTerminal extends Terminal{

	public RobotTerminal(GPConfiguration a_conf, Class<?> a_returnType) throws InvalidConfigurationException {
		super(a_conf, a_returnType);
	}

}
