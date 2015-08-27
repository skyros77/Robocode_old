/* TODO
 * Figure out optimal target to shoot
 * For Melee battles target lock my opponent but occasionally sweep the field
 */

package mo.Data;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import mo.Utils.*;
import robocode.util.Utils;

public class Radar extends Data {

	// VARIABLES
	private static double radarDir = 1;
	private static boolean sweep = true;
	private static String target;

	// CONSTRUCTOR
	public Radar() {
		setRadarDir();
	}

	// METHODS
	public void update() {
		if (eMap.size() == numBots && sweep == true)
			sweepRadar();
	}

	// Single target radar lock
	public void singleLock() {
		r.setTurnRadarRightRadians(Utils.normalRelativeAngle(eAbsBearing - rRadarHeading));
	}

	// calcuate and lock on to optimal target
	public void lockRadar2() {
		double highScore = 0;
		double eAbsBearing = 0;
		for (Entry<String, HashMap<String, Object>> entry : eMap.entrySet()) {
			double eScore = (Double) entry.getValue().get("eScore");
			if (eScore >= highScore) {
				highScore = eScore;
				eAbsBearing = (Double) entry.getValue().get("eAbsBearing");
			}
		}
		r.setTurnRadarRightRadians(Utils.normalRelativeAngle(eAbsBearing - rRadarHeading));
		if (round % 30 == 0 && numBots > 1) sweep = true;
	}

	public void sweepRadar2() {
		Entry<String, HashMap<String, Object>> map = eMap.entrySet().iterator().next();
		double b = (Double) map.getValue().get("eAbsBearing");
		r.setTurnRadarRightRadians(Utils.normalRelativeAngle(b - rRadarHeading) * Double.POSITIVE_INFINITY);

		//r.turnRadarRightRadians(Math.PI*2);
		sweep = false;
	}

	public void sweepRadar() {
		if (round % 30 == 0 && numBots > 1) {

			//sweep = false;
			Entry<String, HashMap<String, Object>> map = eMap.entrySet().iterator().next();
			double b = (Double) map.getValue().get("eAbsBearing");
			r.setTurnRadarRightRadians(Utils.normalRelativeAngle(b - rRadarHeading) * Double.POSITIVE_INFINITY);
		}
		
		double highScore = 0;
		double eAbsBearing = 0;
		for (Entry<String, HashMap<String, Object>> entry : eMap.entrySet()) {
			double eScore = (Double) entry.getValue().get("eScore");
			if (eScore >= highScore) {
				highScore = eScore;
				eAbsBearing = (Double) entry.getValue().get("eAbsBearing");
			}
		}
		r.setTurnRadarRightRadians(Utils.normalRelativeAngle(eAbsBearing - rRadarHeading));
		//if (round % 30 == 0 && numBots > 1) sweep = true;
		
	}

	/*
	 * // Melee radar. Sweeps to oldest target - works but disabled public void sweepRadar() { if (eMap.size() == numBots) { Entry<String, HashMap<String, Object>> map =
	 * eMap.entrySet().iterator().next(); double eAbsBearing = (Double) map.getValue().get("eAbsBearing"); r.setTurnRadarRightRadians(Utils.normalRelativeAngle(eAbsBearing - rRadarHeading) *
	 * Double.POSITIVE_INFINITY); } }
	 */

	// ACCESSORS & MUTATORS
	public static void setRadarDir() {
		setRadarDir(rPos, centre);
	}

	public static void setRadarDir(double angle) {
		radarDir = Utils.normalRelativeAngle(angle);
	}

	public static void setRadarDir(Point2D.Double source, Point2D.Double target) {
		radarDir = Utils.normalRelativeAngle(BotUtils.getAbsBearing(source, target) - rRadarHeading);
	}

	public static double getRadarDir() {
		return radarDir;
	}
}