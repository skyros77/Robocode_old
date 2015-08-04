/* 
 * TO DO
 * Figure out optimal target to shoot
 * For Melee battles target lock my opponent but occasionally sweep the field
 */

package mo.Data;
import mo.Utils.*;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map.Entry;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import robocode.util.*;

public class Radar {

	// VARIABLES
	private static AdvancedRobot r;
	private static Point2D.Double rPos;
	private static Point2D.Double centre;
	private static Point2D.Double target;
	private static double radarDir;
	private static double absBearing;

	// CONSTRUCTOR
	public Radar(AdvancedRobot robot) {
		r = robot;
		rPos = new Point2D.Double(r.getX(), r.getY());
		centre = new Point2D.Double(r.getBattleFieldWidth() / 2, r.getBattleFieldHeight() / 2);
		radarDir = Utils.normalRelativeAngle(MyUtils.getAbsBearing(rPos, centre) - r.getRadarHeadingRadians());	
		target = centre;		
	}

	// METHODS
	public void update(ScannedRobotEvent e) {
		updateVars();
		
		if(r.getOthers() == 1)
			lockRadar(e);
		else
			sweepRadar(e);
	}

	
	/*
	 * update variables
	 */
	private void updateVars() {
		rPos = new Point2D.Double(r.getX(), r.getY());
	}
	
	
	/*
	 * Perfect lock for a single target
	 */
	public void lockRadar(ScannedRobotEvent e) {
		target = MyUtils.getPos(rPos, e.getBearingRadians() + r.getHeadingRadians(), e.getDistance());
		double turn = r.getHeadingRadians() + e.getBearingRadians() - r.getRadarHeadingRadians();
		r.setTurnRadarRightRadians(Utils.normalRelativeAngle(turn));
	}
	
	/*
	 * Melee Radar.  Sweeps to oldest target
	 */
	public void sweepRadar(ScannedRobotEvent e) {
		if (Data.eMap().size() == r.getOthers()) {
			Entry<String, HashMap<String, Object>> map = Data.eMap().entrySet().iterator().next();
			target = (Point2D.Double) map.getValue().get("pos");
			absBearing = (Double) map.getValue().get("absBearing");
			radarDir = Utils.normalRelativeAngle(absBearing - r.getRadarHeadingRadians());
			r.setTurnRadarRightRadians(radarDir * Double.POSITIVE_INFINITY);			
		}
	}

	// ACCESSORS
	public static Point2D.Double getRadarTarget() {
		return target;
	}

	public static Point2D.Double getRobotPos() {
		return rPos;
	}
	
	public static double getRadarDir() {
		return radarDir;
	}

}