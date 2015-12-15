package jgap.gp.terminal;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.terminal.Terminal;
import org.jgap.util.CloneException;
import org.jgap.util.ICloneable;

public class GetVelocity extends RobotTerminal implements ICloneable {

	private static final long serialVersionUID = 2032650484289356511L;

	public GetVelocity(GPConfiguration conf) throws InvalidConfigurationException {
		super(conf, Terminal.DoubleClass);
	}

	public Object clone() {
		try {
			final GetVelocity result = new GetVelocity(getGPConfiguration());
			return result;
		} catch (Exception ex) {
			throw new CloneException(ex);
		}
	}

	public String getName() {
		return "getVelocity";
	}

	public String toString() {
		return "getVelocity()";
	}
	
	public String toFormattedString(){
		return "getVelocity()";
	}
}