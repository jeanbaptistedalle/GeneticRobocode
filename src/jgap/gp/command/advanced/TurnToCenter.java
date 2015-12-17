package jgap.gp.command.advanced;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.terminal.Terminal;
import org.jgap.util.CloneException;
import org.jgap.util.ICloneable;

import jgap.gp.command.RobotCommand;

public class TurnToCenter extends RobotCommand implements ICloneable {

	private static final long serialVersionUID = -5721510975253426897L;

	public TurnToCenter(GPConfiguration conf) throws InvalidConfigurationException {
		super(conf, 0, Terminal.VoidClass);
	}

	public Object clone() {
		try {
			final TurnToCenter result = new TurnToCenter(getGPConfiguration());
			return result;
		} catch (Exception ex) {
			throw new CloneException(ex);
		}
	}

	public String getName() {
		return "turnToCenter";
	}

	public String toString() {
		return "turnToCenter()";
	}
	
	public String toFormattedString(){
		return "turnToCenter();";
	}
}