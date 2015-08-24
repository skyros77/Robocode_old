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
	private static AdvancedRobot r = get_robot();
	private static double radarDir = 1;

	// CONSTRUCTOR
	public Radar() {
		set_radarDir();
	}

	// METHODS
	public void update() {
		if (get_rNum() == 1) lockRadar();
		else sweepRadar();
	}

	// Single target radar lock
	public void lockRadar() {
		r.setTurnRadarRightRadians(Utils.normalRelativeAngle(get_eAbsBearing() - get_rRadarHeading()));
		// Paint.targetPos(ePos);
	}

	// Melee radar. Sweeps to oldest target
	public void sweepRadar() {
		if (get_eMap().size() == get_rNum()) {
			Entry<String, HashMap<String, Object>> map = get_eMap().entrySet().iterator().next();
			double eAbsBearing = (Double) map.getValue().get("absBearing");
			r.setTurnRadarRightRadians(Utils.normalRelativeAngle(eAbsBearing - get_rRadarHeading()) * Double.POSITIVE_INFINITY);
			// Paint.targetPos(ePos);
		}
	}

	// ACCESSORS & MUTATORS
	public static void set_radarDir() {
		set_radarDir(get_rPos(),get_centre());
	}

	public static void set_radarDir(double angle) {
		radarDir = Utils.normalRelativeAngle(angle);
	}

	public static void set_radarDir(Point2D.Double source, Point2D.Double target) {
		radarDir = Utils.normalRelativeAngle(MyUtils.getAbsBearing(source, target) - get_rRadarHeading());
	}

	public static double get_radarDir() {
		return radarDir;
	}
}