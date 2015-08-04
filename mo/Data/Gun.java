package mo.Data;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

import mo.Utils.MyUtils;

import robocode.*;
import robocode.util.Utils;

public class Gun {

	// VARIABLES
	private static AdvancedRobot r;
	private static Point2D.Double ePos;
	private static Point2D.Double rPos;
	private static double FIRE_POWER = 3;
	private static double FIRE_SPEED = Rules.getBulletSpeed(FIRE_POWER);
	private static double pHeading;

	private static List<Point2D.Double> predictions = new ArrayList<Point2D.Double>();

	// CONSTRUCTORS
	public Gun(AdvancedRobot robot) {
		r = robot;
	}

	// METHODS
	public void update(ScannedRobotEvent e) {
		doSingleTickGun(e);
	}

	public void doSingleTickGun(ScannedRobotEvent e) {
		/*
		 * basic single tick predictive shooter, works very well for linear and
		 * radial movements
		 */
		predictions.clear();
		rPos = new Point2D.Double(r.getX(), r.getY());
		ePos = MyUtils.getPos(rPos, e.getBearingRadians() + r.getHeadingRadians(), e.getDistance());

		double cHeading = e.getHeadingRadians();
		double diff = cHeading - pHeading;

		for (int i = 0; i < rPos.distance(ePos) / FIRE_SPEED; i++) {
			cHeading += diff;
			ePos = MyUtils.getPos(ePos, diff + cHeading, e.getVelocity());
			predictions.add(ePos);
		}

		pHeading = e.getHeadingRadians();

		// turn gun
		double absoluteBearing = Math.atan2(ePos.x - rPos.x, ePos.y - rPos.y);
		double gunTurn = absoluteBearing - r.getGunHeadingRadians();
		r.setTurnGunRightRadians(Utils.normalRelativeAngle(gunTurn));

		// fire gun
		if (r.getGunTurnRemainingRadians() < .1)
			r.setFire(FIRE_POWER);
	}

	// ACCESSORS
	public static List<Double> getPredictions() {
		return predictions;
	}
}
