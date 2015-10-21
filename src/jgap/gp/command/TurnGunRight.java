package jgap.gp.command;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;
import org.jgap.util.CloneException;
import org.jgap.util.ICloneable;

public class TurnGunRight extends RobotCommand implements IMutateable, ICloneable {

	private static final long serialVersionUID = 5764407271945140818L;

	private Class m_type;

	public TurnGunRight(final GPConfiguration conf, final Class attributeType) throws InvalidConfigurationException {
		super(conf, 1, CommandGene.VoidClass);
		m_type = attributeType;
	}

	public void execute_void(final ProgramChromosome c, int n, final Object[] args) {
		if (m_type == CommandGene.DoubleClass) {
			double temp = c.execute_double(n, 0, args);
		} else {
			throw new RuntimeException("Class not supported");
		}
	}

	public CommandGene applyMutation(int index, double a_percentage) throws InvalidConfigurationException {
		final TurnGunRight mutant = new TurnGunRight(getGPConfiguration(), m_type);
		return mutant;
	}

	public Class getChildType(final IGPProgram a_ind, int a_chromNum) {
		return m_type;
	}

	public Object clone() {
		try {
			final TurnGunRight result = new TurnGunRight(getGPConfiguration(), m_type);
			return result;
		} catch (Exception ex) {
			throw new CloneException(ex);
		}
	}

	public String getName() {
		return "TurnGunRight";
	}

	public String toString() {
		return "TurnGunRight(&1)";
	}
}
