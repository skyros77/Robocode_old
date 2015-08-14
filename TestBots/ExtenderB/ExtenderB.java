package TestBots.ExtenderB;
import TestBots.Extender;
import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

public class ExtenderB extends Extender {

	// VARIABLES
	private static AdvancedRobot r;
	
	//CONSTRUCTOR
	public ExtenderB(AdvancedRobot robot) {
		r = robot;
	}
	
	//METHODS
	public static void setGun(ScannedRobotEvent e) {
		System.out.println(
			rPos + "\n" +
			r.getEnergy() + "\n" +
			e.getEnergy()
		);
	}
}
