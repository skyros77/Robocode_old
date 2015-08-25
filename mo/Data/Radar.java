/* TODO
 * Figure out optimal target to shoot
 * For Melee battles target lock my opponent but occasionally sweep the field
 */

package mo.Data;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import mo.Paint.Paint;
import mo.Utils.*;
import robocode.AdvancedRobot;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

public class Radar extends Data {

	// VARIABLES
	private static double radarDir = 1;

	// CONSTRUCTOR
	public Radar() {
		setRadarDir();
	}

	// METHODS
	public void update() {
		if (numBots == 1) {
			lockRadar();
		}
		else {
			if (round%30 == 0) r.turnRadarRightRadians(Math.PI*2);
			else lockRadar();
		}
	}
	
	// Single target radar lock
	public void lockRadar() {
		r.setTurnRadarRightRadians(Utils.normalRelativeAngle(eAbsBearing - rRadarHeading));
	}

	// Melee radar. Sweeps to oldest target - works but disabled
	/*
	public void sweepRadar() {
		if (eMap.size() == numBots) {
			Entry<String, HashMap<String, Object>> map = eMap.entrySet().iterator().next();
			double eAbsBearing = (Double) map.getValue().get("eAbsBearing");
			r.setTurnRadarRightRadians(Utils.normalRelativeAngle(eAbsBearing - rRadarHeading) * Double.POSITIVE_INFINITY);
		}
	}
	*/

	// ACCESSORS & MUTATORS
	public static void setRadarDir() {
		setRadarDir(rPos,centre);
	}

	public static void setRadarDir(double angle) {
		radarDir = Utils.normalRelativeAngle(angle);
	}

	public static void setRadarDir(Point2D.Double source, Point2D.Double target) {
		radarDir = Utils.normalRelativeAngle(MyUtils.getAbsBearing(source, target) - rRadarHeading);
	}

	public static double getRadarDir() {
		return radarDir;
	}
}