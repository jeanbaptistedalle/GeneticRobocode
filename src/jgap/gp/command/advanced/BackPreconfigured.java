package jgap.gp.command.advanced;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.util.CloneException;
import org.jgap.util.ICloneable;

import jgap.gp.command.RobotCommand;

public class BackPreconfigured extends RobotCommand implements ICloneable, IMutateable {

	private static final long serialVersionUID = 5764407271945140818L;

	private double pixels;

	public BackPreconfigured(final GPConfiguration conf, double pixels) throws InvalidConfigurationException {
		super(conf, 0, CommandGene.VoidClass);
		this.pixels = pixels;
	}

	public Object clone() {
		try {
			final BackPreconfigured result = new BackPreconfigured(getGPConfiguration(), pixels);
			return result;
		} catch (Exception ex) {
			throw new CloneException(ex);
		}
	}

	public CommandGene applyMutation(int index, double a_percentage) throws InvalidConfigurationException {
		return new AheadPreconfigured(getGPConfiguration(), pixels);
	}

	public String getName() {
		return "back";
	}

	public String toString() {
		return "back(" + pixels + ")";
	}

	public String toFormattedString() {
		return "back(" + pixels + ");";
	}
}
