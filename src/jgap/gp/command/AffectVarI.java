package jgap.gp.command;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;

public class AffectVarI extends RobotCommand{

	private static final long serialVersionUID = 7141318310208650576L;

	private Class<?> m_type;

	public AffectVarI(final GPConfiguration conf, final Class<?> attributeType) throws InvalidConfigurationException {
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

	public Class<?> getChildType(final IGPProgram a_ind, int a_chromNum) {
		return m_type;
	}

	public String getName() {
		return "affectVarI";
	}

	public String toString() {
		return "i = &1";
	}

	public String toFormattedString() {
		return "i = {0};";
	}
}