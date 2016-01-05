package jgap.gp.command.advanced;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.terminal.Terminal;
import org.jgap.util.CloneException;
import org.jgap.util.ICloneable;

import jgap.gp.command.RobotCommand;

public class TurnGunToEnemy extends RobotCommand implements ICloneable {

	private static final long serialVersionUID = -5721510975253426897L;

	public TurnGunToEnemy(GPConfiguration conf) throws InvalidConfigurationException {
		super(conf, 0, Terminal.VoidClass);
	}

	public Object clone() {
		try {
			final TurnGunToEnemy result = new TurnGunToEnemy(getGPConfiguration());
			return result;
		} catch (Exception ex) {
			throw new CloneException(ex);
		}
	}

	public String getName() {
		return "turnGunToEnemy";
	}

	public String toString() {
		return "turnGunToEnemy()";
	}
	
	public String toFormattedString(){
		return "turnGunToEnemy();";
	}
}