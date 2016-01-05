package jgap.gp.command.advanced;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.terminal.Terminal;
import org.jgap.util.CloneException;
import org.jgap.util.ICloneable;

import jgap.gp.command.RobotCommand;

public class TurnToEnemy extends RobotCommand implements ICloneable {

	private static final long serialVersionUID = -5721510975253426897L;

	public TurnToEnemy(GPConfiguration conf) throws InvalidConfigurationException {
		super(conf, 0, Terminal.VoidClass);
	}

	public Object clone() {
		try {
			final TurnToEnemy result = new TurnToEnemy(getGPConfiguration());
			return result;
		} catch (Exception ex) {
			throw new CloneException(ex);
		}
	}

	public String getName() {
		return "turnToEnemy";
	}

	public String toString() {
		return "turnToEnemy()";
	}
	
	public String toFormattedString(){
		return "turnToEnemy();";
	}
}