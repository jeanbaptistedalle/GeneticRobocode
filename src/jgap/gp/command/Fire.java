package jgap.gp.command;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;
import org.jgap.util.CloneException;
import org.jgap.util.ICloneable;

public class Fire extends RobotCommand implements ICloneable{

	private static final long serialVersionUID = 7141318310208650576L;

	private Class m_type;

	public Fire(final GPConfiguration conf, final Class attributeType) throws InvalidConfigurationException {
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

	public Class getChildType(final IGPProgram a_ind, int a_chromNum) {
		return m_type;
	}

	public Object clone() {
		try {
			final Fire result = new Fire(getGPConfiguration(), m_type);
			return result;
		} catch (Exception ex) {
			throw new CloneException(ex);
		}
	}

	public String getName() {
		return "Fire";
	}

	public String toString() {
		return "Fire(&1)";
	}
}