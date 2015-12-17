package jgap.gp.command.advanced;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.util.CloneException;
import org.jgap.util.ICloneable;

import jgap.gp.command.RobotCommand;

public class FirePreconfigured extends RobotCommand implements ICloneable {

	private static final long serialVersionUID = 5764407271945140818L;

	private double power;

	public FirePreconfigured(final GPConfiguration conf, double power) throws InvalidConfigurationException {
		super(conf, 0, CommandGene.VoidClass);
		this.power = power;
	}

	public Object clone() {
		try {
			final FirePreconfigured result = new FirePreconfigured(getGPConfiguration(), power);
			return result;
		} catch (Exception ex) {
			throw new CloneException(ex);
		}
	}

	public String getName() {
		return "fire";
	}

	public String toString() {
		return "fire(" + power + ")";
	}

	public String toFormattedString() {
		return "fire(" + power + ");";
	}
}
