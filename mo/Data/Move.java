/* TODO
 * Check if enemy linear velocity direction is the same as mine, if it is, reverse direction.
 * Use antigrav or soemthing similar to drive bot direction
 * Check for chase bots and adjust movement profile
 * Create a movement pattern that trys to force enemies into corners
 */

package mo.Data;

import mo.Utils.*;
import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

public class Move extends Radar {

	// VARIABLES
	private static AdvancedRobot r;
	private static double rHeading;	
	private static double eDistance;
	private static double eAbsBearing;		
	
	private static double distance = 200; //desired target distance
	private static int dir = 1;


	// CONSTRUCTORS
	public Move(AdvancedRobot robot) {
		r = robot;
	}

	// METHODS
	public void update(ScannedRobotEvent e) {
		updateVars();
		doMove();
	}
	
	private void updateVars() {
		rHeading	= get_rHeading();
		eDistance	= get_eDistance();
		eAbsBearing	= get_eAbsBearing();		
	}
	
	private void doMove() {
		// set random direction - TEMP
		if (r.getTime() % MyUtils.randomNum(5, 30) == 0) dir *= -1;

		/*
		 * turn bot perpendicular to target using shortest distance possible
		 * adjust turn angle to compensate for optimal distance
		 */	
		double range = MyUtils.clampRange(MyUtils.normalizeRange(eDistance, distance, distance+100), -1, 1);
		double angle = Utils.normalRelativeAngle(eAbsBearing - rHeading);
		double turn = angle < 0 ? angle + (Math.PI / 2) - (range * dir) : angle - (Math.PI / 2) + (range * dir);

		r.setAhead(100 * dir);
		r.setTurnRightRadians(turn);
	}
}
