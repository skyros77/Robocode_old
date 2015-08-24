/* TODO
 * 
 */

package mo.Data;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

import mo.Paint.Paint;
import mo.Utils.MyUtils;

import robocode.*;
import robocode.util.Utils;

public class Gun extends Data {

	// VARIABLES
	private static AdvancedRobot r = get_robot();
	private static double firePower = 1;
	private static double fireSpeed = Rules.getBulletSpeed(firePower);
	private static double pHeading;
	private static List<Point2D.Double> predictions = new ArrayList<Point2D.Double>();

	// CONSTRUCTORS
	public Gun() {
		r = get_robot();
	}

	// METHODS
	public void update() {
		doSingleTickGun();
	}

	// single tick predictive gun
	public void doSingleTickGun() {
		predictions.clear();
		double cHeading = get_eHeading();
		double diff = cHeading - pHeading;
		Double tPos = get_ePos();

		for (int i = 0; i < get_rPos().distance(tPos) / fireSpeed; i++) {
			cHeading += diff;
			tPos = MyUtils.getPos(tPos, cHeading + diff, get_eVelocity());
			predictions.add(tPos);
		}

		pHeading = get_eHeading();

		// turn gun
		double turn = MyUtils.getAbsBearing(get_rPos(), tPos);
		r.setTurnGunRightRadians(Utils.normalRelativeAngle(turn - get_rGunHeading()));

		// fire gun
		if (r.getGunTurnRemainingRadians() < .1) {
			r.setFire(firePower);
		}

		// Paint Debug
		Paint.predictions(predictions);
	}

	// ACCESSORS & MUTATORS
	public static List<Double> get_Predictions() {
		return predictions;
	}
}
