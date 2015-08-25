/* TODO
 * Check if enemy linear velocity direction is the same as mine, if it is, reverse direction.
 * Use antigrav or soemthing similar to drive bot direction
 * Check for chase bots and adjust movement profile
 * Create a movement pattern that trys to force enemies into corners
 */

package mo.Data;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import mo.Paint.*;
import mo.Utils.*;
import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

public class Move extends Data {

	// VARIABLES
	//private static AdvancedRobot r;
	private static double turn;
	private static double angle;
	private static double dist = 200; //optimal target distance
	
	private static int dir = 1; //direction of travel
	private static int buffer = 18; //wall buffer

	// CONSTRUCTORS
	public Move(AdvancedRobot robot) {
		//r = get_robot();
	}

	// METHODS
	public void update(ScannedRobotEvent e) {
		doMove();
		wallSmooth();
	}



	private void doMove() {
		// set random direction - TEMP
		if (r.getTime() % BotUtils.randomNum(5, 30) == 0) dir *= -1;
		// if (r.getTime() % 30 == 0) dir *= -1;

		/*
		 * turn bot perpendicular to target using shortest distance possible adjust turn angle to compensate for optimal distance
		 */

		double range = BotUtils.clampRange(BotUtils.normalizeRange(eDistance, dist, dist + 100), -.1, 1);
		angle = Utils.normalRelativeAngle(eAbsBearing - rHeading);
		turn = angle < 0 ? angle + (Math.PI / 2) - (range * dir) : angle - (Math.PI / 2) + (range * dir);
		// r.setAhead(100 * dir);
		// r.setTurnRightRadians(turn);

	}

	private void wallSmooth() {

		//Rectangle2D.Double field = new Rectangle2D.Double(buffer, buffer, field - buffer * 2, r.getBattleFieldHeight() - buffer * 2);

		rVelocity = Math.abs(rVelocity);
		double rad = (rVelocity / ((10 - 0.75 * rVelocity) / (180 / Math.PI)));
		Point2D.Double feeler = BotUtils.getPos(rPos, rHeading, (rad * dir));
		Point2D.Double pivot = (angle > 0) ? BotUtils.getPos(rPos, rHeading + Math.PI / 2 * dir, (rad * dir)) : BotUtils.getPos(rPos, rHeading - Math.PI / 2 * dir, (rad * dir));
		Rectangle2D.Double bbox = new Rectangle2D.Double(pivot.x - Math.abs(rad * dir), pivot.y - Math.abs(rad * dir), Math.abs(rad * dir) * 2, Math.abs(rad * dir) * 2);
		rVelocity = (!field.contains(feeler) && !field.contains(bbox)) ? 8-rVelocity+1 : 8; // if collision imminent reduce speed
		//rVelocity = (!field.contains(bbox) && !field.contains(feeler)) ? 8 : 8; // if collision imminent reduce speed
		//rVelocity = 8;
	
		if (!field.contains(feeler) && !field.contains(bbox)) {
			turn = angle * Double.POSITIVE_INFINITY;
			dir *= -1;
			System.out.println("evasive maneuvers");
		}

		r.setMaxVelocity(rVelocity);
		r.setAhead(100 * dir);
		r.setTurnRightRadians(turn);

		
		//debug
		/*
		Paint.line(rPos, feeler);
		Paint.oval(pivot, Math.abs(rad * 2));
		Paint.rectangle(bbox);
		Paint.rectangle(field);
		*/
	}
}
