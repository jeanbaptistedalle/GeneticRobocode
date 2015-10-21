package jgap.gp.command;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;
import org.jgap.util.CloneException;
import org.jgap.util.ICloneable;

public class IfThen extends RobotCommand implements ICloneable {
	private static final long serialVersionUID = -5903644847798673420L;

	private Class[] m_types;

	public IfThen(final GPConfiguration conf, final Class attributeTypes) throws InvalidConfigurationException {
			super(conf, 2, CommandGene.VoidClass);
			m_types = new Class[2];
			m_types[0] = attributeTypes;
			m_types[1] = CommandGene.VoidClass;
		}

	public void execute_void(final ProgramChromosome c, int n, final Object[] args) {
		boolean condition;
		if (m_types[0] == CommandGene.DoubleClass) {
			condition = c.execute_double(n, 0, args) > 0;
		} else if (m_types[0] == CommandGene.BooleanClass) {
			condition = c.execute_boolean(n, 0, args);
		} else {
			throw new RuntimeException("Class not supported");
		}
		if (condition)

		{
			c.execute_void(n, 1, args);
		} else

		{
			c.execute_void(n, 2, args);
		}

	}

	public Class getChildType(final IGPProgram a_ind, int a_chromNum) {
		return m_types[a_chromNum];
	}

	public Object clone() {
		try {
			IfThen result = new IfThen(getGPConfiguration(), m_types[0]);
			return result;
		} catch (Exception ex) {
			throw new CloneException(ex);
		}
	}

	public String getName() {
		return "IfThen";
	}

	public String toString() {
		if (m_types[0] == CommandGene.DoubleClass) {
			return "If (&1 > 0) Then (&2)";
		} else if (m_types[0] == CommandGene.BooleanClass) {
			return "If (&1) Then (&2)";
		} else {
			throw new RuntimeException("Class not supported");
		}
	}
}
