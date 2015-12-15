package com.univ.angers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.GregorianCalendar;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

/**
 * @author etudiant
 *
 */
public class Robot {

	private String robotCode;
	private String robotName;
	private String robotPackage;

	public Robot() {

	}

	/**
	 * Method which compile a robot in order to obtain a .class
	 * 
	 * @return true if compilation succeed else false
	 */
	private boolean compileRobotClass() {
		final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		final DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		final StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
		final JavaSourceFromString sourceClass = new JavaSourceFromString(GeneralVariables.ROBOTS_FOLDER + robotName,
				robotCode);
		final Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(sourceClass);
		final CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);
		boolean success = task.call();

		try {
			fileManager.close();
			if (success) {
				final StringBuilder commandLine = new StringBuilder();
				commandLine.append("mv ");
				commandLine.append(robotName);
				commandLine.append(".class");
				commandLine.append(" robots/");
				commandLine.append(getFormatedPackage());
				commandLine.append(robotName);
				commandLine.append(".class");
				final Runtime rt = Runtime.getRuntime();
				rt.exec(commandLine.toString());
			}
		} catch (IOException e) {
			// TODO
		}
		return success;
	}

	/**
	 * Method which create a jar file and place it into robots folder
	 */
	private boolean makeRobotJarFile() {
		final StringBuilder commandLineBuilder = new StringBuilder();
		commandLineBuilder.append("jar cvf robots/");
		commandLineBuilder.append(getFormatedPackage());
		commandLineBuilder.append(robotName);
		commandLineBuilder.append(".jar robots/");
		commandLineBuilder.append(getFormatedPackage());
		commandLineBuilder.append(robotName);
		commandLineBuilder.append(".class");
		final String commandLine = commandLineBuilder.toString();
		final Runtime rt = Runtime.getRuntime();
		try {
			rt.exec(commandLine);
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	/**
	 * Method which create a property file and place it into robots folder
	 */
	private boolean makeRobotPropertyFile() {
		final StringBuilder propertyBuilder = new StringBuilder();
		propertyBuilder.append("#Robot Properties").append(GeneralVariables.NEW_LINE);
		final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		propertyBuilder.append("#").append(sdf.format(new GregorianCalendar().getTime()))
				.append(GeneralVariables.NEW_LINE);
		propertyBuilder.append("robot.description= ").append(GeneralVariables.NEW_LINE);
		propertyBuilder.append("robot.webpage=").append(GeneralVariables.NEW_LINE);
		propertyBuilder.append("robocode.version=1").append(GeneralVariables.NEW_LINE);
		propertyBuilder.append("robot.java.source.included=true").append(GeneralVariables.NEW_LINE);
		propertyBuilder.append("robot.author.name=univ angers").append(GeneralVariables.NEW_LINE);
		propertyBuilder.append("robot.classname=");
		if (robotPackage != null && robotPackage != "") {
			propertyBuilder.append(robotPackage).append(".");
		}
		propertyBuilder.append(robotName).append(GeneralVariables.NEW_LINE);
		propertyBuilder.append("robot.name=").append(robotName).append(GeneralVariables.NEW_LINE);
		final String fileName;
		if (robotPackage != null && robotPackage != "") {
			fileName = "robots/" + robotPackage + "/" + robotName + ".properties";
		} else {
			fileName = "robots/" + robotName + ".properties";
		}
		final File f = new File(fileName);
		try {
			final Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
			writer.write(propertyBuilder.toString());
			writer.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	/**
	 * Method which create a .java, compile it into a .class, compress it into a
	 * .jar and then create a property file
	 */
	public void makeRobot() {
		if (!compileRobotClass()) {
			throw new RuntimeException("Compilation of robot \"" + robotName + "\" failed.");
		}
		if (!makeRobotPropertyFile()) {
			throw new RuntimeException("Creation of robot \"" + robotName + "\"'s property file failed.");
		}
		if (!makeRobotJarFile()) {
			throw new RuntimeException("Creation of robot \"" + robotName + "\"'s jar file failed.");
		}
	}

	public void clean() {
		new File(getJavaName()).delete();
		new File(getClassName()).delete();
		new File(getPropertiesName()).delete();
		new File(getJarName()).delete();
	}

	public String getRobotCode() {
		return robotCode;
	}

	public void setRobotCode(String robotCode) {
		this.robotCode = robotCode;
	}

	public String getRobotName() {
		return robotName;
	}

	public void setRobotName(String robotName) {
		this.robotName = robotName;
	}

	public String getRobotPackage() {
		return robotPackage;
	}

	public void setRobotPackage(String robotPackage) {
		this.robotPackage = robotPackage;
	}

	public String getFormatedPackage() {
		if (robotPackage != null && robotPackage != "") {
			return robotPackage + "/";
		}
		return "";
	}

	public String getJavaName() {
		return GeneralVariables.ROBOTS_FOLDER + getFormatedPackage() + this.robotName + ".java";
	}

	public String getClassName() {
		return GeneralVariables.ROBOTS_FOLDER + getFormatedPackage() + this.robotName + ".class";
	}

	public String getPropertiesName() {
		return GeneralVariables.ROBOTS_FOLDER + getFormatedPackage() + this.robotName + ".properties";
	}

	public String getJarName() {
		return GeneralVariables.ROBOTS_FOLDER + getFormatedPackage() + this.robotName + ".jar";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((robotName == null) ? 0 : robotName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Robot other = (Robot) obj;
		if (robotName == null) {
			if (other.robotName != null)
				return false;
		} else if (!robotName.equals(other.robotName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Robot [robotName=" + robotName + "]";
	}
}
