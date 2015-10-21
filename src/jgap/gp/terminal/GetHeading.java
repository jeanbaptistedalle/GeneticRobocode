package jgap.gp.terminal;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.terminal.Terminal;
import org.jgap.util.CloneException;
import org.jgap.util.ICloneable;

public class GetHeading extends RobotTerminal implements ICloneable {

	private static final long serialVersionUID = 2032650484289356511L;

	public GetHeading(GPConfiguration conf) throws InvalidConfigurationException {
		super(conf, Terminal.DoubleClass);
	}

	public Object clone() {
		try {
			final GetHeading result = new GetHeading(getGPConfiguration());
			return result;
		} catch (Exception ex) {
			throw new CloneException(ex);
		}
	}

	public String getName() {
		return "GetHeading";
	}

	public String toString() {
		return "GetHeading()";
	}
}