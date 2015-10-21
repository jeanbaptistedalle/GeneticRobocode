package jgap.gp.terminal;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.terminal.Terminal;
import org.jgap.util.CloneException;
import org.jgap.util.ICloneable;

public class GetX extends RobotTerminal implements IMutateable, ICloneable {

	private static final long serialVersionUID = 8595011172078426854L;

	public GetX(GPConfiguration conf) throws InvalidConfigurationException {
		super(conf, Terminal.DoubleClass);
	}

	public CommandGene applyMutation(int index, double a_percentage) throws InvalidConfigurationException {
		final GetX mutant = new GetX(getGPConfiguration());
		return mutant;
	}

	public Object clone() {
		try {
			final GetY result = new GetY(getGPConfiguration());
			return result;
		} catch (Exception ex) {
			throw new CloneException(ex);
		}
	}

	public String getName() {
		return "GetX";
	}

	public String toString() {
		return "GetX()";
	}

}
