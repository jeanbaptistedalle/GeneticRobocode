package jgap.gp.command;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;

public class Fire extends RobotCommand {

	private static final long serialVersionUID = 7141318310208650576L;

	public Fire(final GPConfiguration conf) throws InvalidConfigurationException {
		super(conf);
	}

	public String toString() {
		return "Fire";
	}
}