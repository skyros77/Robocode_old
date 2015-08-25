/* TODO
 * 
 */

package mo.Data;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

import mo.Paint.Paint;
import mo.Utils.BotUtils;

import robocode.Rules;
import robocode.util.Utils;

public class Gun extends Data {

	// VARIABLES
	private static double firePower = 1;
	private static double fireSpeed = Rules.getBulletSpeed(firePower);
	private static double pHeading;
	
	private static List<Point2D.Double> predictions = new ArrayList<Point2D.Double>();

	// CONSTRUCTORS
	public Gun() {}

	// METHODS
	public void update() {
		doSingleTickGun();
	}

	// single tick predictive gun
	public void doSingleTickGun() {
		predictions.clear();
		double cHeading = eHeading;
		double diff = cHeading - pHeading;
		Double tPos = ePos;

		for (int i = 0; i < rPos.distance(tPos) / fireSpeed; i++) {
			cHeading += diff;
			tPos = BotUtils.getPos(tPos, cHeading + diff, eVelocity);
			predictions.add(tPos);
		}

		pHeading = eHeading;

		// turn gun
		double turn = BotUtils.getAbsBearing(rPos, tPos);
		r.setTurnGunRightRadians(Utils.normalRelativeAngle(turn - rGunHeading));

		// fire gun
		if (r.getGunTurnRemainingRadians() < .1) {
			r.setFire(firePower);
		}

		// Paint Debug
		Paint.predictions(predictions);
	}

	// ACCESSORS & MUTATORS
	public static List<Double> getPredictions() {
		return predictions;
	}
}
