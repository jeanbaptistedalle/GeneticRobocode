package jgap.gp.terminal;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.terminal.Terminal;
import org.jgap.util.CloneException;
import org.jgap.util.ICloneable;

public class GetVarI extends RobotTerminal implements IMutateable, ICloneable {

	private static final long serialVersionUID = 8595011172078426854L;

	public GetVarI(GPConfiguration conf) throws InvalidConfigurationException {
		super(conf, Terminal.DoubleClass);
	}

	public CommandGene applyMutation(int index, double a_percentage) throws InvalidConfigurationException {
		final GetVarI mutant = new GetVarI(getGPConfiguration());
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
		return "getVarI";
	}

	public String toString() {
		return "getVarI()";
	}
	
	public String toFormattedString(){
		return "i";
	}

}
