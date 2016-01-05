package jgap.gp.command.advanced;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.util.CloneException;
import org.jgap.util.ICloneable;

import jgap.gp.command.RobotCommand;

public class TurnLeftPreconfigured extends RobotCommand implements ICloneable, IMutateable {

	private static final long serialVersionUID = 5764407271945140818L;

	private double degree;

	public TurnLeftPreconfigured(final GPConfiguration conf, double degree) throws InvalidConfigurationException {
		super(conf, 0, CommandGene.VoidClass);
		this.degree = degree;
	}

	public Object clone() {
		try {
			final TurnLeftPreconfigured result = new TurnLeftPreconfigured(getGPConfiguration(), degree);
			return result;
		} catch (Exception ex) {
			throw new CloneException(ex);
		}
	}

	public CommandGene applyMutation(int index, double a_percentage) throws InvalidConfigurationException {
		return new TurnRightPreconfigured(getGPConfiguration(), degree);
	}

	public String getName() {
		return "turnLeft";
	}

	public String toString() {
		return "turnLeft("+degree+")";
	}

	public String toFormattedString() {
		return "turnLeft("+degree+");";
	}
}
