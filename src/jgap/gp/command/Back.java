package jgap.gp.command;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;

public class Back extends RobotCommand implements IMutateable {

	private static final long serialVersionUID = -5339823060712505234L;

	private Class<?> m_type;

	public Back(final GPConfiguration conf, final Class<?> attributeType) throws InvalidConfigurationException {
		super(conf, 1, CommandGene.VoidClass);
		m_type = attributeType;
	}

	public void execute_void(final ProgramChromosome c, int n, final Object[] args) {
		if (m_type == CommandGene.DoubleClass) {
		} else {
			throw new RuntimeException("Class not supported");
		}
	}

	public CommandGene applyMutation(int index, double a_percentage) throws InvalidConfigurationException {
		final Ahead mutant = new Ahead(getGPConfiguration(), m_type);
		return mutant;
	}

	public Class<?> getChildType(final IGPProgram a_ind, int a_chromNum) {
		return m_type;
	}

	public String getName() {
		return "back";
	}

	public String toString() {
		return "back(&1)";
	}

	public String toFormattedString() {
		return "back({0});";
	}
}