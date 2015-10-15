package com.univ.angers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * Ajouter dans le .bashrc "export ROBOCODE_HOME=<robocode home>"
 * 
 * @author etudiant
 *
 */
public class Launcher {

	public static String HOME = "/home/etudiant/robocode/libs";
	public static String JVM_ARGUMENTS = " -Xmx512M -Dsun.io.useCanonCaches=false -Ddebug=true";
	public static String ROBOCODE_ARGUMENTS = " -nodisplay -nosound";
	public static String BATTLE_ARGUMENT = " -battle ";
	public static String RESULT_ARGUMENT = " -results ";

	/**
	 * Methode permettant de compiler un robot afin d'obtenir un .class
	 * 
	 * @param class
	 *            code du robot
	 * @param className
	 *            nom de la classe du robot
	 * @return true si compilation réussie, false sinon
	 */
	private boolean compileRobotClass(String robotCode, String className) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

		JavaSourceFromString sourceClass = new JavaSourceFromString(className, robotCode);

		Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(sourceClass);
		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null,
				compilationUnits);
		boolean success = task.call();
		try {
			fileManager.close();
		} catch (IOException e) {
			// TODO
		}
		return success;
	}

	/**
	 * Methode permettant de créer le fichier jar d'un robot et de le placer
	 * dans le dossier robots
	 * 
	 * @param className
	 *            nom de la classe du robot
	 */
	private void makeRobotJarFile(String className) {
		StringBuilder commandLineBuilder = new StringBuilder();
		commandLineBuilder.append("jar cvf robots/");
		commandLineBuilder.append(className);
		commandLineBuilder.append(".jar ");
		commandLineBuilder.append(className);
		commandLineBuilder.append(".class");
		String commandLine = commandLineBuilder.toString();
		Runtime rt = Runtime.getRuntime();
		try {
			Process pr = rt.exec(commandLine);
		} catch (IOException e) {
			// TODO
		}
	}
	
	/**
	 * Methode permettant d'executer robocode avec un fichier battle et un
	 * fichier result donné.\n !! Les robots doivent déjà être présent sous
	 * forme de .jar dans le fichier robots
	 * 
	 * @param battleFile
	 * @param resultFile
	 */
	private void launchRobocode(String battleFile, String resultFile) {
		// Preparation de la ligne de commande permettant d'executer robocode
		StringBuilder commandLineBuilder = new StringBuilder();
		commandLineBuilder.append("java");
		commandLineBuilder.append(JVM_ARGUMENTS);
		commandLineBuilder.append(" -cp ");
		String robocode_home = System.getenv("ROBOCODE_HOME");
		if (robocode_home == null) {
			System.out.println("Please add \"export ROBOCODE_HOME=<robocode home>\" to your .bashrc");
			return;
		}
		commandLineBuilder.append(robocode_home);
		commandLineBuilder.append("/libs/robocode.jar");
		commandLineBuilder.append(" robocode.Robocode");
		commandLineBuilder.append(ROBOCODE_ARGUMENTS);
		commandLineBuilder.append(BATTLE_ARGUMENT);
		commandLineBuilder.append(battleFile);
		commandLineBuilder.append(RESULT_ARGUMENT);
		commandLineBuilder.append(resultFile);
		String commandLine = commandLineBuilder.toString();
		System.out.println("Executing \"" + commandLine + "\" ...");
		Runtime rt = Runtime.getRuntime();
		try {
			Process pr = rt.exec(commandLine);
			InputStream is = pr.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO
		}
	}

	public static void main(String[] args) {

	}
}
