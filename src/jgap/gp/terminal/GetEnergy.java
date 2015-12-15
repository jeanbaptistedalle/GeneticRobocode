package jgap.gp.terminal;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.terminal.Terminal;
import org.jgap.util.CloneException;
import org.jgap.util.ICloneable;

public class GetEnergy extends RobotTerminal implements ICloneable {

	private static final long serialVersionUID = -5721510975253426897L;

	public GetEnergy(GPConfiguration conf) throws InvalidConfigurationException {
		super(conf, Terminal.DoubleClass);
	}

	public Object clone() {
		try {
			final GetEnergy result = new GetEnergy(getGPConfiguration());
			return result;
		} catch (Exception ex) {
			throw new CloneException(ex);
		}
	}

	public String getName() {
		return "getEnergy";
	}

	public String toString() {
		return "getEnergy()";
	}
	
	public String toFormattedString(){
		return "getEnergy()";
	}
}