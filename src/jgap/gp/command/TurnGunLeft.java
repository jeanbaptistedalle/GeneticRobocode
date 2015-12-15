package jgap.gp.command;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;

public class TurnGunLeft extends RobotCommand implements IMutateable {

	private static final long serialVersionUID = 5764407271945140818L;

	private Class<?> m_type;

	public TurnGunLeft(final GPConfiguration conf, final Class<?> attributeType) throws InvalidConfigurationException {
		super(conf, 1, CommandGene.VoidClass);
		m_type = attributeType;
	}

	public void execute_void(final ProgramChromosome c, int n, final Object[] args) {
		if (m_type == CommandGene.DoubleClass) {
			c.execute_double(n, 0, args);
		} else {
			throw new RuntimeException("Class not supported");
		}
	}

	public CommandGene applyMutation(int index, double a_percentage) throws InvalidConfigurationException {
		final TurnGunRight mutant = new TurnGunRight(getGPConfiguration(), m_type);
		return mutant;
	}

	public Class<?> getChildType(final IGPProgram a_ind, int a_chromNum) {
		return m_type;
	}

	public String getName() {
		return "turnGunLeft";
	}

	public String toString() {
		return "turnGunLeft(&1)";
	}

	public String toFormattedString() {
		return "turnGunLeft({0});";
	}
}
