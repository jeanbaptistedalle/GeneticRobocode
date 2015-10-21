package jgap.gp.command;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;

public class TurnGunLeft extends RobotCommand implements IMutateable {

	private static final long serialVersionUID = 5764407271945140818L;

	public TurnGunLeft(final GPConfiguration conf) throws InvalidConfigurationException {
		super(conf);
	}

	public CommandGene applyMutation(int index, double a_percentage) throws InvalidConfigurationException {
		TurnGunRight mutant = new TurnGunRight(getGPConfiguration());
		return mutant;
	}

	public String toString() {
		return "TurnGunLeft";
	}
}
