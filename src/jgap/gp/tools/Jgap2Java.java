package jgap.gp.tools;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.jgap.gp.CommandGene;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.function.Add;
import org.jgap.gp.function.And;
import org.jgap.gp.function.Divide;
import org.jgap.gp.function.Equals;
import org.jgap.gp.function.GreaterThan;
import org.jgap.gp.function.Multiply;
import org.jgap.gp.function.Or;
import org.jgap.gp.function.SubProgram;
import org.jgap.gp.function.Subtract;
import org.jgap.gp.impl.ProgramChromosome;
import org.jgap.gp.terminal.NOP;
import org.jgap.gp.terminal.Terminal;

import com.univ.angers.GeneralVariables;
import com.univ.angers.Robot;
import com.univ.angers.RobotFactory;

import jgap.gp.command.Ahead;
import jgap.gp.command.Back;
import jgap.gp.command.Fire;
import jgap.gp.command.IfThen;
import jgap.gp.command.IfThenElse;
import jgap.gp.command.TurnGunLeft;
import jgap.gp.command.TurnGunRight;
import jgap.gp.command.TurnLeft;
import jgap.gp.command.TurnRadarLeft;
import jgap.gp.command.TurnRadarRight;
import jgap.gp.command.TurnRight;
import jgap.gp.terminal.GetEnergy;
import jgap.gp.terminal.GetGunHeading;
import jgap.gp.terminal.GetHeading;
import jgap.gp.terminal.GetRadarHeading;
import jgap.gp.terminal.GetVelocity;
import jgap.gp.terminal.GetX;
import jgap.gp.terminal.GetY;

public class Jgap2Java {
	// run, onScannedRobot, onHitWall, onHitByBullet
	public static Robot getRobotFromChrom(final IGPProgram prog, final String robotPackage) {
		String[] code = new String[GeneralVariables.NUMBER_OF_CHROMOSOME];
		for (int i = 0; i < GeneralVariables.NUMBER_OF_CHROMOSOME; i++) {
			final ProgramChromosome chrom = prog.getChromosome(i);
			code[i] = Jgap2Java.getJavaCodeFromCommand(prog, chrom, chrom.getNode(0), 0);
		}
		return RobotFactory.getInstance().buildGenRobot("Generobot" + prog.getGPConfiguration().getGenerationNr(), robotPackage, code);
	}

	private static String getJavaCodeFromCommand(final IGPProgram prog, final ProgramChromosome chrom, final CommandGene command,
			int index) {
		final String pattern = getPatternFromCommand(prog, chrom, command);
		final MessageFormat c = new MessageFormat(pattern);
		final List<String> childs = new ArrayList<String>();
		int arity = command.getArity(prog);
		if (arity == 0) {
			final Class<? extends CommandGene> clazz = command.getClass();
			if (clazz == NOP.class) {
				return "";
			}
			if (clazz == GetEnergy.class) {
				return c.format(new Object[] { "getEnergy()" });
			}
			if (clazz == GetGunHeading.class) {
				return c.format(new Object[] { "getGunHeading()" });
			}
			if (clazz == GetHeading.class) {
				return c.format(new Object[] { "getHeading()" });
			}
			if (clazz == GetRadarHeading.class) {
				return c.format(new Object[] { "getRadarHeading()" });
			}
			if (clazz == GetVelocity.class) {
				return c.format(new Object[] { "getVelocity()" });
			}
			if (clazz == GetX.class) {
				return c.format(new Object[] { "getX()" });
			}
			if (clazz == GetY.class) {
				return c.format(new Object[] { "getY()" });
			}
			if (command.getReturnType() == CommandGene.DoubleClass) {
				return c.format(new Object[] { command.execute_double(chrom, 0, null) });
			}
			if (command.getReturnType() == CommandGene.BooleanClass) {
				return c.format(new Object[] { command.execute_boolean(chrom, 0, null) });
			}
			throw new RuntimeException("Class \"" + command.getClass() + "\" not supported");
		} else {
			for (int i = 0; i < arity; i++) {
				int childIndex = chrom.getChild(index, i);
				final CommandGene child = chrom.getNode(childIndex);
				childs.add(getJavaCodeFromCommand(prog, chrom, child, childIndex));
			}
			return c.format(childs.toArray());
		}
	}

	private static String getPatternFromCommand(final IGPProgram prog, final ProgramChromosome chrom, final CommandGene command) {
		final Class<? extends CommandGene> clazz = command.getClass();
		if (clazz == NOP.class) {
			return "";
		}
		if (clazz == Terminal.class || command instanceof Terminal) {
			return "{0}";
		}
		if (clazz == Add.class) {
			return "{0} + {1}";
		}
		if (clazz == Subtract.class) {
			return "{0} - {1}";
		}
		if (clazz == Multiply.class) {
			return "{0} * {1}";
		}
		if (clazz == Divide.class) {
			return "{0} * {1}";
		}
		if (clazz == GreaterThan.class) {
			return "{0} > {1}";
		}
		if (clazz == Or.class) {
			return "{0} || {1}";
		}
		if (clazz == And.class) {
			return "{0} && {1}";
		}
		if (clazz == Equals.class) {
			return "{0} == {1}";
		}
		if (clazz == SubProgram.class) {
			final StringBuilder retour = new StringBuilder();
			for (int i = 0; i < command.getArity(prog); i++) {
				retour.append("{");
				retour.append(i);
				retour.append("}\n");
			}
			return retour.toString();
		}
		if (clazz == IfThenElse.class) {
			if (command.getChildType(prog, 0) == CommandGene.DoubleClass) {
				return "if({0} > 0)'{' {1} '}'else'{' {2} '}'";
			} else if (command.getChildType(prog, 0) == CommandGene.BooleanClass) {
				return "if({0})'{' {1} '}'else'{' {2} '}'";
			} else {
				throw new RuntimeException("Class \"" + command.getClass() + "\" not supported");
			}
		}
		if (clazz == IfThen.class) {
			if (command.getChildType(prog, 0) == CommandGene.DoubleClass) {
				return "if({0} > 0)'{' {1} '}'";
			} else if (command.getChildType(prog, 0) == CommandGene.BooleanClass) {
				return "if({0})'{' {1} '}'";
			} else {
				throw new RuntimeException("Class \"" + command.getClass() + "\" not supported");
			}
		}
		if (clazz == Ahead.class) {
			return "ahead({0});";
		}
		if (clazz == Back.class) {
			return "back({0});";
		}
		if (clazz == Fire.class) {
			return "fire({0});";
		}
		if (clazz == TurnGunLeft.class) {
			return "turnGunLeft({0});";
		}
		if (clazz == TurnGunRight.class) {
			return "turnGunRight({0});";
		}
		if (clazz == TurnRadarLeft.class) {
			return "turnRadarLeft({0});";
		}
		if (clazz == TurnRadarRight.class) {
			return "turnRadarRight({0});";
		}
		if (clazz == TurnLeft.class) {
			return "turnLeft({0});";
		}
		if (clazz == TurnRight.class) {
			return "turnRight({0});";
		}
		throw new RuntimeException("Class \"" + command.getClass() + "\" not supported");
	}
}
