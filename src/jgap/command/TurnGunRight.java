package jgap.command;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;

public class TurnGunRight extends RobotCommand implements IMutateable {

	private static final long serialVersionUID = 8211903762838754858L;

	public TurnGunRight(final GPConfiguration conf) throws InvalidConfigurationException {
		super(conf);
	}

	public CommandGene applyMutation(int index, double a_percentage) throws InvalidConfigurationException {
		TurnGunLeft mutant = new TurnGunLeft(getGPConfiguration());
		return mutant;
	}

	public String toString() {
		return "TurnGunRight";
	}
}
