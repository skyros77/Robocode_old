package mo.Data;
import robocode.AdvancedRobot;

public class Move {
	
	//VARIABLES
	private static AdvancedRobot myBot;

	//METHODS
	public void setVars() {
		myBot = Data.getMyBot();
	}
	
	public void update() {
		setVars();
		myBot.setAhead(200);
	}
	
	
}
