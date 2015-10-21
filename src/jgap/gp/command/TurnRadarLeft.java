package jgap.gp.command;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;

public class TurnRadarLeft extends RobotCommand implements IMutateable {

	private static final long serialVersionUID = 680867116608092922L;

	public TurnRadarLeft(final GPConfiguration conf) throws InvalidConfigurationException {
		super(conf);
	}

	public CommandGene applyMutation(int index, double a_percentage) throws InvalidConfigurationException {
		TurnRadarRight mutant = new TurnRadarRight(getGPConfiguration());
		return mutant;
	}

	public String toString() {
		return "TurnRadarLeft";
	}
}
