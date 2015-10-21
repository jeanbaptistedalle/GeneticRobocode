package jgap.gp.command;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;

public class Ahead extends RobotCommand implements IMutateable {

	private static final long serialVersionUID = 8760293710431620964L;

	public Ahead(final GPConfiguration conf) throws InvalidConfigurationException {
		super(conf, 1, CommandGene.VoidClass);
	}
	
	public void execute_void(final GPConfiguration conf, int n, final Object[] args){
		
	}

	public CommandGene applyMutation(int index, double a_percentage) throws InvalidConfigurationException {
		Back mutant = new Back(getGPConfiguration());
		return mutant;
	}

	public String toString() {
		return "Ahead";
	}
}
