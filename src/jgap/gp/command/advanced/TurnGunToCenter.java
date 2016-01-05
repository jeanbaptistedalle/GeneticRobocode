package jgap.gp.command.advanced;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.terminal.Terminal;
import org.jgap.util.CloneException;
import org.jgap.util.ICloneable;

import jgap.gp.command.RobotCommand;

public class TurnGunToCenter extends RobotCommand implements ICloneable {

	private static final long serialVersionUID = -5721510975253426897L;

	public TurnGunToCenter(GPConfiguration conf) throws InvalidConfigurationException {
		super(conf, 0, Terminal.VoidClass);
	}

	public Object clone() {
		try {
			final TurnGunToCenter result = new TurnGunToCenter(getGPConfiguration());
			return result;
		} catch (Exception ex) {
			throw new CloneException(ex);
		}
	}

	public String getName() {
		return "turnGunToCenter";
	}

	public String toString() {
		return "turnGunToCenter()";
	}
	
	public String toFormattedString(){
		return "turnGunToCenter();";
	}
}