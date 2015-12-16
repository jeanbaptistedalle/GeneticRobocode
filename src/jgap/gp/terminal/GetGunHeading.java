package jgap.gp.terminal;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.terminal.Terminal;
import org.jgap.util.CloneException;
import org.jgap.util.ICloneable;

public class GetGunHeading extends RobotTerminal implements ICloneable {

	private static final long serialVersionUID = -2631781174273981908L;

	public GetGunHeading(GPConfiguration conf) throws InvalidConfigurationException {
		super(conf, Terminal.DoubleClass);
	}

	public Object clone() {
		try {
			final GetGunHeading result = new GetGunHeading(getGPConfiguration());
			return result;
		} catch (Exception ex) {
			throw new CloneException(ex);
		}
	}

	public String getName() {
		return "getGunHeading";
	}

	public String toString() {
		return "getGunHeading()";
	}
	
	public String toFormattedString(){
		return "getGunHeading()";
	}
}