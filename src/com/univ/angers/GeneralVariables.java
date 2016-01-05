package com.univ.angers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author etudiant
 *
 */
public class GeneralVariables {

	public final static String JVM_ARGUMENTS = " -Xmx512M -Dsun.io.useCanonCaches=false -Ddebug=true";
	public final static String ROBOCODE_ARGUMENTS = " -nodisplay -nosound";
	public String temp = "";
	public final static String BATTLE_ARGUMENT = " -battle ";
	public final static String RESULT_ARGUMENT = " -results ";
	public final static String RESULTS_FOLDER = "results/";
	public final static String ROBOT_PACKAGE = "robots";
	public final static String ROBOTS_FOLDER = "robots/";
	public final static String BATTLES_FOLDER = "battles/";
	public final static String ROBOCODE_ENV = "ROBOCODE_HOME";
	public final static String GENERATION_ROBOT_PACKAGE = "generation";
	public final static String ROBOT_TEST_PACKAGE = "test";
	public final static String BEST_ROBOT_PACKAGE = "best";
	public final static String NEW_LINE = "\n";
	public final static String DOUBLE_LINE = "\n\n";
	public final static int BATTLE_HEIGHT = 600;
	public final static int BATTLE_WIDTH = 800;
	public final static int NUMBER_OF_ROUND = 5;
	public final static int POPULATION_SIZE = 30;
	public final static int NUMBER_OF_EVOLUTION = 100;
	public final static int NUMBER_OF_CHROMOSOME = 4;
	public final static String BLOC_GENE_FILE_PATH = "blog_gene_file.txt";
	public final static String BLOC_GENE_RUN_PATH = "gene_run.txt";
	public final static String BLOC_GENE_ONSCAN_PATH = "gene_onscan.txt";
	public final static String BLOC_GENE_ONHITROBOT_PATH = "gene_onhitrobot.txt";
	public final static String BLOC_GENE_ONHITWALL_PATH = "gene_onhitwall.txt";
	public final static int GP_POPULATION_SIZE = 30;
	public final static int GP_NUMBER_OF_BLOCS = 5;
	public final static int GP_NUMBER_OF_BATTLE = 3;
	public final static int GP_NUMBER_OF_ROUND = 5;
	public final static int GP_NUMBER_OF_TEST_ROBOT = 3;

	public static List<String> getTestRobotsName() {
		final List<String> robots = new ArrayList<String>() {
			private static final long serialVersionUID = -8803657930774508702L;

			{
				add("sample.CirclingBot");
				add("sample.Corners");
				add("sample.Crazy");
				add("sample.Fire");
				add("sample.RamFire");
				add("sample.SpinBot");
				add("sample.Tracker");
				add("sample.TrackFire");
				add("sample.VelociRobot");
				add("sample.Walls");
			}
		};
		return robots;
	}
}
