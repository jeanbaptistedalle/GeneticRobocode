package jgap.gp.command.advanced;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.util.CloneException;
import org.jgap.util.ICloneable;

import jgap.gp.command.RobotCommand;

public class TurnGunRightPreconfigured extends RobotCommand implements ICloneable, IMutateable {

	private static final long serialVersionUID = 5764407271945140818L;

	private double degree;

	public TurnGunRightPreconfigured(final GPConfiguration conf, double degree) throws InvalidConfigurationException {
		super(conf, 0, CommandGene.VoidClass);
		this.degree = degree;
	}

	public Object clone() {
		try {
			final TurnGunRightPreconfigured result = new TurnGunRightPreconfigured(getGPConfiguration(), degree);
			return result;
		} catch (Exception ex) {
			throw new CloneException(ex);
		}
	}

	public CommandGene applyMutation(int index, double a_percentage) throws InvalidConfigurationException {
		return new TurnGunLeftPreconfigured(getGPConfiguration(), degree);
	}

	public String getName() {
		return "turnGunLeft";
	}

	public String toString() {
		return "turnGunLeft(" + degree + ")";
	}

	public String toFormattedString() {
		return "turnGunLeft(" + degree + ");";
	}
}
