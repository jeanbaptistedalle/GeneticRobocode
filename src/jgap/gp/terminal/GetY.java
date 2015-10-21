package jgap.gp.terminal;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.terminal.Terminal;
import org.jgap.util.CloneException;
import org.jgap.util.ICloneable;

public class GetY extends RobotTerminal implements IMutateable, ICloneable {

	private static final long serialVersionUID = -6691293457603512722L;

	public GetY(GPConfiguration conf) throws InvalidConfigurationException {
		super(conf, Terminal.DoubleClass);
	}

	public CommandGene applyMutation(int index, double a_percentage) throws InvalidConfigurationException {
		final GetY mutant = new GetY(getGPConfiguration());
		return mutant;
	}

	public Object clone() {
		try {
			final GetX result = new GetX(getGPConfiguration());
			return result;
		} catch (Exception ex) {
			throw new CloneException(ex);
		}
	}

	public String getName() {
		return "GetY";
	}

	public String toString() {
		return "GetY()";
	}

}
