package mo.Data;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

public class Gun {

	// VARIABLES
	private static AdvancedRobot r;

	// CONSTRUCTORS
	public Gun(AdvancedRobot robot) {
		r = robot;
	}

	// METHODS
	public void update(ScannedRobotEvent e) {
		doGun();
	}

	public void doGun() {
	}
}
