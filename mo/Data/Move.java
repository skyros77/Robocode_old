/* TODO
 * Check if enemy linear velocity direction is the same as mine, if it is, reverse direction.
 * Use antigrav or soemthing similar to drive bot direction
 * Check for chase bots and adjust movement profile
 * Create a movement pattern that trys to force enemies into corners
 */

package mo.Data;

import java.awt.geom.Point2D;

import mo.Utils.*;
import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

public class Move {

	// VARIABLES
	private static AdvancedRobot r;
	private static Point2D.Double myPos;
	private static Point2D.Double ePos;
	private static int dir = 1;
	private static double angle;
	private static double range;
	private static double distance = 250; //desired target distance
	private static double absBearing;
	private static double turn;

	// CONSTRUCTORS
	public Move(AdvancedRobot robot) {
		r = robot;
	}

	// METHODS
	public void update(ScannedRobotEvent e) {

		// set random direction - TEMP
		if (r.getTime() % MyUtils.randomNum(5, 30) == 0) dir *= -1;

		/*
		 * turn bot perpendicular to target using shortest distance possible
		 * check desired range to target and adjust turn angle to compensate 
		 */
		myPos = new Point2D.Double(r.getX(), r.getY());
		ePos = MyUtils.getPos(myPos, e.getBearingRadians() + r.getHeadingRadians(), e.getDistance());
		absBearing = MyUtils.getAbsBearing(myPos, ePos);
		range = MyUtils.clampRange(MyUtils.normalizeRange(e.getDistance(), distance, distance+100), -1, 1); //desired range = 300	
		angle = Utils.normalRelativeAngle(absBearing - r.getHeadingRadians());
		turn = angle < 0 ? angle + (Math.PI / 2) - (range * dir) : angle - (Math.PI / 2) + (range * dir);

		r.setAhead(100 * dir);
		r.setTurnRightRadians(turn);

		//System.out.println(e.getDistance() +"/"+ range);
	}

}
