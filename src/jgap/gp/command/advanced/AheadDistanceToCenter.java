package jgap.gp.command.advanced;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.terminal.Terminal;
import org.jgap.util.CloneException;
import org.jgap.util.ICloneable;

import jgap.gp.command.RobotCommand;

public class AheadDistanceToCenter extends RobotCommand implements ICloneable, IMutateable {

	private static final long serialVersionUID = -5721510975253426897L;

	public AheadDistanceToCenter(GPConfiguration conf) throws InvalidConfigurationException {
		super(conf, 0, Terminal.VoidClass);
	}

	public Object clone() {
		try {
			final AheadDistanceToCenter result = new AheadDistanceToCenter(getGPConfiguration());
			return result;
		} catch (Exception ex) {
			throw new CloneException(ex);
		}
	}

	public CommandGene applyMutation(int index, double a_percentage) throws InvalidConfigurationException {
		return new BackDistanceToCenter(getGPConfiguration());
	}

	public String getName() {
		return "aheadDistanceToCenter";
	}

	public String toString() {
		return "aheadDistanceToCenter()";
	}

	public String toFormattedString() {
		return "aheadDistanceToCenter();";
	}
}