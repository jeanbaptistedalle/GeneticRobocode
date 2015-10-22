package com.univ.angers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

/**
 * 
 * @author etudiant
 *
 */
public class GeneticRobocode {

	private static GeneticRobocode INSTANCE = null;

	private GeneticRobocode() {

	}

	/**
	 * Method which allow to execute robocode with a battle file and a result
	 * file.\n !! Robots have to be in the robots forlder and in .jar format
	 * 
	 * @param battleFile
	 * @param resultFile
	 */
	public void launchRobocode(final String battleFile, final String resultFile) {
		// Preparation de la ligne de commande permettant d'executer robocode
		final StringBuilder commandLineBuilder = new StringBuilder();
		commandLineBuilder.append("java");
		commandLineBuilder.append(GeneralVariables.JVM_ARGUMENTS);
		commandLineBuilder.append(" -cp ");
		String robocode_home = System.getenv(GeneralVariables.ROBOCODE_ENV);
		commandLineBuilder.append(robocode_home);
		commandLineBuilder.append("/libs/robocode.jar robocode.Robocode");
		commandLineBuilder.append(GeneralVariables.ROBOCODE_ARGUMENTS);
		commandLineBuilder.append(GeneralVariables.BATTLE_ARGUMENT);
		commandLineBuilder.append(battleFile);
		commandLineBuilder.append(GeneralVariables.RESULT_ARGUMENT);
		commandLineBuilder.append(" ").append(GeneralVariables.RESULTS_FOLDER);
		commandLineBuilder.append(resultFile);
		final String commandLine = commandLineBuilder.toString();
		System.out.println("Executing \"" + commandLine + "\" ...");
		final Runtime rt = Runtime.getRuntime();
		try {
			final Process pr = rt.exec(commandLine);
			final InputStream is = pr.getInputStream();
			final BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO
		}
	}

	public void buildBattleFile(final String battleName, final List<Robot> robots) {
		final StringBuilder battle = new StringBuilder();
		battle.append("#Battle Properties\n");
		battle.append("robocode.battleField.width=").append(GeneralVariables.BATTLE_WIDTH)
				.append(GeneralVariables.NEW_LINE);
		battle.append("robocode.battleField.height=").append(GeneralVariables.BATTLE_HEIGHT)
				.append(GeneralVariables.NEW_LINE);
		battle.append("robocode.battle.numRounds=100").append(GeneralVariables.NEW_LINE);
		battle.append("robocode.battle.gunCoolingRate=0.1").append(GeneralVariables.NEW_LINE);
		battle.append("robocode.battle.rules.inactivityTime=450").append(GeneralVariables.NEW_LINE);
		battle.append("robocode.battle.hideEnemyNames=true").append(GeneralVariables.NEW_LINE);
		battle.append("robocode.battle.selectedRobots=");
		for (final Robot robot : robots) {
			battle.append(GeneralVariables.ROBOT_PACKAGE).append(".").append(robot.getRobotName()).append("*")
					.append(",");
		}
		battle.deleteCharAt(battle.length() - 1);
		final File f = new File(GeneralVariables.BATTLES_FOLDER + battleName);
		try {
			final Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
			writer.write(battle.toString());
			writer.close();
		} catch (IOException e) {
			// TODO
		}
	}

	/**
	 * Method which check the configuration to run GeneticRobocode (environment,
	 * folders, etc.)
	 * 
	 * @return
	 */
	public boolean checkConfig() {
		final String robocode_home = System.getenv(GeneralVariables.ROBOCODE_ENV);
		if (robocode_home == null) {
			System.out.println(
					"Please add \"export " + GeneralVariables.ROBOCODE_ENV + "=<robocode home>\" to your .bashrc");
			return false;
		}

		final File robotPackage = new File(GeneralVariables.ROBOTS_FOLDER + GeneralVariables.ROBOT_PACKAGE);
		if (!robotPackage.exists()) {
			robotPackage.mkdirs();
		}
		if (!robotPackage.exists()) {
			return false;
		}

		final File robotTestPackage = new File(GeneralVariables.ROBOTS_FOLDER + GeneralVariables.ROBOT_TEST_PACKAGE);
		if (!robotTestPackage.exists()) {
			robotTestPackage.mkdirs();
		}
		if (!robotTestPackage.exists()) {
			return false;
		}

		final File bestRobotPackage = new File(GeneralVariables.ROBOTS_FOLDER + GeneralVariables.BEST_ROBOT_PACKAGE);
		if (!bestRobotPackage.exists()) {
			bestRobotPackage.mkdirs();
		}
		if (!bestRobotPackage.exists()) {
			return false;
		}

		final File battlesFolder = new File(GeneralVariables.BATTLES_FOLDER);
		if (!battlesFolder.exists()) {
			battlesFolder.mkdirs();
		}
		if (!battlesFolder.exists()) {
			return false;
		}

		final File resultsFolder = new File(GeneralVariables.RESULTS_FOLDER);
		if (!resultsFolder.exists()) {
			resultsFolder.mkdirs();
		}
		if (!resultsFolder.exists()) {
			return false;
		}
		return true;
	}

	public static GeneticRobocode getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new GeneticRobocode();
		}
		return INSTANCE;
	}

}
