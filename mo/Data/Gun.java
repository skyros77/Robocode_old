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
	private static double firePower = 0;
	private static double fireSpeed = Rules.getBulletSpeed(firePower);
	private static double pHeading;
	private static double absBearing;
	private static double diff;

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
		 * single tick predictive gun
		 */
		predictions.clear();
		rPos = new Point2D.Double(r.getX(), r.getY());
		ePos = MyUtils.getPos(rPos, e.getBearingRadians() + r.getHeadingRadians(), e.getDistance());

		double cHeading = e.getHeadingRadians();
		diff = cHeading - pHeading;

		for (int i = 0; i < rPos.distance(ePos) / fireSpeed; i++) {
			cHeading += diff;
			ePos = MyUtils.getPos(ePos, cHeading + diff, e.getVelocity());
			predictions.add(ePos);
		}

		pHeading = e.getHeadingRadians();

		// turn gun
		absBearing = MyUtils.getAbsBearing(rPos, ePos);
		r.setTurnGunRightRadians(Utils.normalRelativeAngle(absBearing - r.getGunHeadingRadians()));

		// fire gun
		if (r.getGunTurnRemainingRadians() < .1) {
			r.setFire(firePower);
		}
	}

	// ACCESSORS
	public static List<Double> getPredictions() {
		return predictions;
	}
}
