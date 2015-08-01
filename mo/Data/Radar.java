/* 
 * TO DO
 * Set up radar for single enemy
 * For Melee battles target lock my opponent but occasionally sweep the field
 */

package mo.Data;
import mo.Utils.*;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map.Entry;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

public class Radar {

	// VARIABLES
	private static AdvancedRobot r;
	private static Point2D.Double myPos;
	private static Point2D.Double centre;
	private static Point2D.Double target;
	private static double radarDir;
	private static double absBearing;

	// CONSTRUCTOR
	public Radar(AdvancedRobot robot) {
		r = robot;
		myPos = new Point2D.Double(r.getX(), r.getY());
		centre = new Point2D.Double(r.getBattleFieldWidth() / 2, r.getBattleFieldHeight() / 2);
		target = centre;
		radarDir = Utils.normalRelativeAngle(MyUtils.getAbsBearing(myPos, centre) - r.getRadarHeadingRadians());
	}

	// METHODS
	public void update(ScannedRobotEvent e) {
		setRadarDir();

	}

	public void setRadarDir() {
		if (Data.eMap().size() == r.getOthers()) {
			Entry<String, HashMap<String, Object>> map = Data.eMap().entrySet().iterator().next();
			target = (Point2D.Double) map.getValue().get("pos");
			absBearing = (Double) map.getValue().get("absBearing");
			radarDir = Utils.normalRelativeAngle(absBearing - r.getRadarHeadingRadians());
		}
	}

	// ACCESSORS
	public static Point2D.Double getRadarTargetPos() {
		return target;
	}

	public static double getRadarDir() {
		return radarDir;
	}

}