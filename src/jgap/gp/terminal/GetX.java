package jgap.gp.terminal;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.terminal.Terminal;

public class GetX extends Terminal {

	private static final long serialVersionUID = 5411274607149144463L;

	public GetX(GPConfiguration conf) throws InvalidConfigurationException {
		super(conf, Terminal.DoubleClass);
	}
	
	public String toString() {
		return "GetX";
	}

}
