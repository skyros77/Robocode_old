package mo.Data;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

import mo.Utils.MyUtils;

import robocode.*;
import robocode.util.Utils;

public class Gun extends Radar{

	// VARIABLES
	private static AdvancedRobot r;
	private static Point2D.Double rPos;
	private static Point2D.Double ePos;
	private static double rGunHeading;	
	private static double eVelocity;
	private static double eHeading;	
	private static double firePower = 0;
	private static double fireSpeed = Rules.getBulletSpeed(firePower);
	private static double pHeading;

	private static List<Point2D.Double> predictions = new ArrayList<Point2D.Double>();

	// CONSTRUCTORS
	public Gun(AdvancedRobot robot) {
		r = robot;
	}

	// METHODS
	public void update(ScannedRobotEvent e) {
		updateVars(e);
		doSingleTickGun(e);
	}

	// update variables
	private void updateVars(ScannedRobotEvent e) {
		rPos 		= get_rPos();
		rGunHeading	= get_rGunHeading();
		eVelocity	= get_eVelocity();
		eHeading	= get_eHeading();		
		ePos		= get_ePos();
	}
	
	public void doSingleTickGun(ScannedRobotEvent e) {
		/*
		 * single tick predictive gun
		 */
		predictions.clear();
		double cHeading = eHeading;
		double diff = cHeading - pHeading;

		for (int i = 0; i < rPos.distance(ePos) / fireSpeed; i++) {
			cHeading += diff;
			ePos = MyUtils.getPos(ePos, cHeading + diff, eVelocity);
			predictions.add(ePos);
		}

		pHeading = eHeading;

		// turn gun
		double turn = MyUtils.getAbsBearing(rPos, ePos);
		r.setTurnGunRightRadians(Utils.normalRelativeAngle(turn - rGunHeading));

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
