package param.numeric.ag;

import com.univ.angers.GeneralVariables;


import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSpecification;

public class Battle {

	private int score;
	
	public Battle() {
		// TODO Auto-generated constructor stub
	}
	
	public void lanchBattle(String robots, String myrobot, boolean gui) {
	      // Disable log messages from Robocode
	      RobocodeEngine.setLogMessagesEnabled(false);

	      // Create the RobocodeEngine
	      //   RobocodeEngine engine = new RobocodeEngine(); // Run from current working directory
	      //RobocodeEngine engine = new RobocodeEngine(new java.io.File(System.getenv(GeneralVariables.ROBOCODE_ENV))); // Run from C:/Robocode
	      RobocodeEngine engine = new RobocodeEngine();

	      // Add our own battle listener to the RobocodeEngine
	      BattleObserver battleObserver = new BattleObserver(myrobot);
	      engine.addBattleListener(battleObserver);

	      // Show the Robocode battle view
	      engine.setVisible(gui);

	      // Setup the battle specification

	      int numberOfRounds = 5;
	      BattlefieldSpecification battlefield = new BattlefieldSpecification(800, 600); // 800x600
	      RobotSpecification[] selectedRobots = engine.getLocalRepository(robots);
	      

	      BattleSpecification battleSpec = new BattleSpecification(numberOfRounds, battlefield, selectedRobots);

	      // Run our specified battle and let it run till it is over
	      engine.runBattle(battleSpec, true); // waits till the battle finishes
	      
	      score = battleObserver.getIndivduScore();
	      // Cleanup our RobocodeEngine
	      engine.close();

	      // Make sure that the Java VM is shut down properly
//	      System.exit(0);
		
	}

	
	public int getResult() {
		
		return score;
	}

}
