package jgap.command;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;

public class TurnRadarRight extends RobotCommand implements IMutateable {

	private static final long serialVersionUID = -4472283596991022344L;

	public TurnRadarRight(final GPConfiguration conf) throws InvalidConfigurationException {
		super(conf);
	}

	public CommandGene applyMutation(int index, double a_percentage) throws InvalidConfigurationException {
		TurnRadarLeft mutant = new TurnRadarLeft(getGPConfiguration());
		return mutant;
	}

	public String toString() {
		return "TurnRadarRight";
	}
}
