package parametrage.ag;


import robocode.control.events.BattleAdaptor;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.BattleErrorEvent;
import robocode.control.events.BattleMessageEvent;

class BattleObserver extends BattleAdaptor {
	
	private int score;
	private String myrobot;
	
	public BattleObserver(String myrobot) {
		this.myrobot = myrobot;
	}
	
	// Called when the battle is completed successfully with battle results
    public void onBattleCompleted(BattleCompletedEvent e) {
        System.out.println("-- Battle has completed --");
        
        // Print out the sorted results with the robot names
        System.out.println("Battle results:");
        for (robocode.BattleResults result : e.getSortedResults()) {
        	if (result.getTeamLeaderName().contains(myrobot)) 
        							score = result.getScore();
        	System.out.println("  " + result.getTeamLeaderName() + ": " + result.getScore());
            
        }
        
    }

    // Called when the game sends out an information message during the battle
    public void onBattleMessage(BattleMessageEvent e) {
        System.out.println("Msg> " + e.getMessage());
    }

    // Called when the game sends out an error message during the battle
    public void onBattleError(BattleErrorEvent e) {
        System.out.println("Err> " + e.getError());
    }
    
    public int getIndivduScore() {
    	return score;
    }
}