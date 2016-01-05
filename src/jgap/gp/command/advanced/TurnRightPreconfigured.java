package jgap.gp.command.advanced;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.util.CloneException;
import org.jgap.util.ICloneable;

import jgap.gp.command.RobotCommand;

public class TurnRightPreconfigured extends RobotCommand implements ICloneable, IMutateable {

	private static final long serialVersionUID = 5764407271945140818L;

	private double degree;

	public TurnRightPreconfigured(final GPConfiguration conf, double degree) throws InvalidConfigurationException {
		super(conf, 0, CommandGene.VoidClass);
		this.degree = degree;
	}

	public Object clone() {
		try {
			final TurnRightPreconfigured result = new TurnRightPreconfigured(getGPConfiguration(), degree);
			return result;
		} catch (Exception ex) {
			throw new CloneException(ex);
		}
	}

	public CommandGene applyMutation(int index, double a_percentage) throws InvalidConfigurationException {
		return new TurnLeftPreconfigured(getGPConfiguration(), degree);
	}

	public String getName() {
		return "turnRight";
	}

	public String toString() {
		return "turnRight("+degree+")";
	}

	public String toFormattedString() {
		return "turnRight("+degree+");";
	}
}

