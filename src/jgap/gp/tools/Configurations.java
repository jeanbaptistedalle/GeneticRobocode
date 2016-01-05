package jgap.gp.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.function.Add;
import org.jgap.gp.function.And;
import org.jgap.gp.function.Divide;
import org.jgap.gp.function.Equals;
import org.jgap.gp.function.GreaterThan;
import org.jgap.gp.function.Multiply;
import org.jgap.gp.function.Or;
import org.jgap.gp.function.SubProgram;
import org.jgap.gp.function.Subtract;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.terminal.NOP;
import org.jgap.gp.terminal.Terminal;

import jgap.gp.command.AffectVarI;
import jgap.gp.command.Ahead;
import jgap.gp.command.Back;
import jgap.gp.command.Fire;
import jgap.gp.command.IfThen;
import jgap.gp.command.IfThenElse;
import jgap.gp.command.TurnGunLeft;
import jgap.gp.command.TurnGunRight;
import jgap.gp.command.TurnLeft;
import jgap.gp.command.TurnRight;
import jgap.gp.command.advanced.AheadDistanceToCenter;
import jgap.gp.command.advanced.AheadDistanceToEnemy;
import jgap.gp.command.advanced.AheadPreconfigured;
import jgap.gp.command.advanced.BackPreconfigured;
import jgap.gp.command.advanced.FirePreconfigured;
import jgap.gp.command.advanced.TurnGunLeftPreconfigured;
import jgap.gp.command.advanced.TurnGunRightPreconfigured;
import jgap.gp.command.advanced.TurnGunToCenter;
import jgap.gp.command.advanced.TurnGunToEnemy;
import jgap.gp.command.advanced.TurnLeftPreconfigured;
import jgap.gp.command.advanced.TurnRightPreconfigured;
import jgap.gp.command.advanced.TurnToCenter;
import jgap.gp.command.advanced.TurnToEnemy;
import jgap.gp.terminal.GetEnergy;
import jgap.gp.terminal.GetGunHeading;
import jgap.gp.terminal.GetHeading;
import jgap.gp.terminal.GetVarI;
import jgap.gp.terminal.GetVelocity;
import jgap.gp.terminal.GetX;
import jgap.gp.terminal.GetY;

public class Configurations {

	/**
	 * Configuration initiale avec des instructions élémentaires simples.
	 * 
	 * @param conf
	 * @return
	 * @throws InvalidConfigurationException
	 */
	public static CommandGene[] getConfigurationInitiale(final GPConfiguration conf) throws InvalidConfigurationException {
		final Collection<CommandGene> commands = new ArrayList<CommandGene>();
		commands.addAll(getTerminal(conf));
		commands.addAll(getCalculCommands(conf));
		commands.addAll(getSubProgramCommands(conf));
		commands.addAll(getLargeSubProgramCommands(conf));
		commands.addAll(getGetterCommands(conf));
		commands.addAll(getActionCommands(conf));
		commands.addAll(getVarICommands(conf));
		commands.addAll(getConditionnalCommands(conf));
		commands.addAll(getBooleanOperatorCommands(conf));
		// commands.addAll(getAdvancedActions(conf));
		return toArray(commands);
	}

	/**
	 * Configuration avec des instructions élémentaires simples mais sans les
	 * opérateurs de calculs ni les subprograms étendus.
	 * 
	 * @param conf
	 * @return
	 * @throws InvalidConfigurationException
	 */
	public static CommandGene[] getConfigurationSansCalcul(final GPConfiguration conf) throws InvalidConfigurationException {
		final Collection<CommandGene> commands = new ArrayList<CommandGene>();
		commands.addAll(getTerminal(conf));
		// commands.addAll(getCalculCommands(conf));
		commands.addAll(getSubProgramCommands(conf));
		// commands.addAll(getLargeSubProgramCommands(conf));
		commands.addAll(getGetterCommands(conf));
		commands.addAll(getActionCommands(conf));
		commands.addAll(getVarICommands(conf));
		commands.addAll(getConditionnalCommands(conf));
		commands.addAll(getBooleanOperatorCommands(conf));
		// commands.addAll(getAdvancedActions(conf));
		return toArray(commands);
	}

	/**
	 * Configuration avec des opérations élémentaiers plus poussées (et donc
	 * sans les élémentaires de bases)
	 * 
	 * @param conf
	 * @return
	 * @throws InvalidConfigurationException
	 */
	public static CommandGene[] getConfigurationAvancee(final GPConfiguration conf) throws InvalidConfigurationException {
		final Collection<CommandGene> commands = new ArrayList<CommandGene>();
		commands.addAll(getTerminal(conf));
		// commands.addAll(getCalculCommands(conf));
		commands.addAll(getSubProgramCommands(conf));
		commands.addAll(getLargeSubProgramCommands(conf));
		commands.addAll(getGetterCommands(conf));
		// commands.addAll(getActionCommands(conf));
		commands.addAll(getVarICommands(conf));
		commands.addAll(getConditionnalCommands(conf));
		commands.addAll(getBooleanOperatorCommands(conf));
		commands.addAll(getAdvancedActions(conf));
		return toArray(commands);
	}

	private static List<CommandGene> getTerminal(final GPConfiguration conf) throws InvalidConfigurationException {
		final List<CommandGene> commands = new ArrayList<CommandGene>();
		commands.add(new Terminal(conf, CommandGene.DoubleClass, 0d, 100d, true));
		commands.add(new NOP(conf));
		return commands;
	}

	private static List<CommandGene> getCalculCommands(final GPConfiguration conf) throws InvalidConfigurationException {
		final List<CommandGene> commands = new ArrayList<CommandGene>();
		commands.add(new Add(conf, CommandGene.DoubleClass));
		commands.add(new Subtract(conf, CommandGene.DoubleClass));
		commands.add(new Multiply(conf, CommandGene.DoubleClass));
		commands.add(new Divide(conf, CommandGene.DoubleClass));
		return commands;
	}

	private static List<CommandGene> getBooleanOperatorCommands(final GPConfiguration conf) throws InvalidConfigurationException {
		final List<CommandGene> commands = new ArrayList<CommandGene>();
		commands.add(new GreaterThan(conf, CommandGene.DoubleClass));
		commands.add(new Equals(conf, CommandGene.DoubleClass));
		commands.add(new And(conf));
		commands.add(new Or(conf));
		return commands;
	}

	private static List<CommandGene> getConditionnalCommands(final GPConfiguration conf) throws InvalidConfigurationException {
		final List<CommandGene> commands = new ArrayList<CommandGene>();
		commands.add(new IfThen(conf, CommandGene.DoubleClass));
		commands.add(new IfThen(conf, CommandGene.BooleanClass));
		commands.add(new IfThenElse(conf, CommandGene.DoubleClass));
		commands.add(new IfThen(conf, CommandGene.BooleanClass));
		return commands;
	}

	private static List<CommandGene> getSubProgramCommands(final GPConfiguration conf) throws InvalidConfigurationException {
		final List<CommandGene> commands = new ArrayList<CommandGene>();
		commands.add(new SubProgram(conf, new Class[] { CommandGene.VoidClass, CommandGene.VoidClass }, true));
		return commands;
	}

	private static List<CommandGene> getLargeSubProgramCommands(final GPConfiguration conf) throws InvalidConfigurationException {
		final List<CommandGene> commands = new ArrayList<CommandGene>();
		commands.add(new SubProgram(conf, new Class[] { CommandGene.VoidClass, CommandGene.VoidClass, CommandGene.VoidClass }, true));
		commands.add(new SubProgram(conf,
				new Class[] { CommandGene.VoidClass, CommandGene.VoidClass, CommandGene.VoidClass, CommandGene.VoidClass }, true));
		return commands;
	}

	private static List<CommandGene> getGetterCommands(final GPConfiguration conf) throws InvalidConfigurationException {
		final List<CommandGene> commands = new ArrayList<CommandGene>();
		commands.add(new GetX(conf));
		commands.add(new GetY(conf));
		commands.add(new GetEnergy(conf));
		commands.add(new GetGunHeading(conf));
		commands.add(new GetHeading(conf));
		commands.add(new GetVelocity(conf));
		return commands;
	}

	private static List<CommandGene> getVarICommands(final GPConfiguration conf) throws InvalidConfigurationException {
		final List<CommandGene> commands = new ArrayList<CommandGene>();
		commands.add(new GetVarI(conf));
		commands.add(new AffectVarI(conf, CommandGene.DoubleClass));
		return commands;
	}

	private static List<CommandGene> getActionCommands(final GPConfiguration conf) throws InvalidConfigurationException {
		final List<CommandGene> commands = new ArrayList<CommandGene>();
		commands.add(new Ahead(conf, CommandGene.DoubleClass));
		commands.add(new Back(conf, CommandGene.DoubleClass));
		commands.add(new Fire(conf, CommandGene.DoubleClass));
		commands.add(new TurnGunLeft(conf, CommandGene.DoubleClass));
		commands.add(new TurnGunRight(conf, CommandGene.DoubleClass));
		commands.add(new TurnLeft(conf, CommandGene.DoubleClass));
		commands.add(new TurnRight(conf, CommandGene.DoubleClass));
		return commands;
	}

	private static List<CommandGene> getAdvancedActions(final GPConfiguration conf) throws InvalidConfigurationException {
		final List<CommandGene> commands = new ArrayList<CommandGene>();
		commands.add(new AheadPreconfigured(conf, 50));
		commands.add(new BackPreconfigured(conf, 50));
		commands.add(new AheadDistanceToCenter(conf));
		commands.add(new TurnToCenter(conf));
		commands.add(new TurnGunToCenter(conf));
		commands.add(new AheadDistanceToEnemy(conf));
		commands.add(new TurnToEnemy(conf));
		commands.add(new TurnGunToEnemy(conf));
		commands.add(new TurnLeftPreconfigured(conf, 10));
		commands.add(new TurnLeftPreconfigured(conf, 90));
		commands.add(new TurnRightPreconfigured(conf, 10));
		commands.add(new TurnRightPreconfigured(conf, 90));
		commands.add(new TurnGunLeftPreconfigured(conf, 5));
		commands.add(new TurnGunLeftPreconfigured(conf, 10));
		commands.add(new TurnGunRightPreconfigured(conf, 5));
		commands.add(new TurnGunRightPreconfigured(conf, 10));
		commands.add(new FirePreconfigured(conf, 1));
		commands.add(new FirePreconfigured(conf, 2));
		commands.add(new FirePreconfigured(conf, 3));
		return commands;
	}

	private static CommandGene[] toArray(final Collection<CommandGene> commands) {
		return commands.toArray(new CommandGene[] {});
	}
}
