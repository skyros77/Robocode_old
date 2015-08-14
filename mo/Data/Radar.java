/* 
 * TO DO
 * Figure out optimal target to shoot
 * For Melee battles target lock my opponent but occasionally sweep the field
 */

package mo.Data;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map.Entry;

import mo.Utils.*;
import mo.Data.Data;
import robocode.AdvancedRobot;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

public class Radar extends Data{

	// VARIABLES
	private static Point2D.Double rPos;
	private static double robotNum;	
	
	

	// VARIABLES
	/*	
	private static AdvancedRobot r;

	private static Point2D.Double rPos;
	private static Point2D.Double ePos;
	private static String eName;
	private static double eVelocity;
	private static double eAbsBearing;
	private static double eDistance;


	private static Point2D.Double target;
	private static double radarDir;
	//private static double absBearing;
	private static LinkedHashMap<String, HashMap<String, Object>> eMap = new LinkedHashMap<String, HashMap<String, Object>>(4, 0.75f, true);
*/
	
	// CONSTRUCTOR
	public Radar() {}
	
	public Radar(AdvancedRobot robot) {}	
	
	// METHODS
	public void update(ScannedRobotEvent e) {
		System.out.println(get_robotNum());
		//updateVars(e);
		//setMap(e);

		if (get_robotNum() == 1) {
			//lockRadar(e);
		}
		else {
			//sweepRadar(e);
		}

	}

	/*
	public void update(RobotDeathEvent e) {
		eMap.remove(e.getName());
	}

	private void updateVars(ScannedRobotEvent e) {
		rPos = new Point2D.Double(r.getX(), r.getY());
		eName = e.getName();
		eVelocity = e.getVelocity();
		eAbsBearing = e.getBearingRadians() + r.getHeadingRadians();
		eDistance = e.getDistance();
		ePos = MyUtils.getPos(rPos, absBearing, eDistance);

	}

	public void setMap(ScannedRobotEvent e) {
		// Add enemy information to Map
		eMap.put(eName, new HashMap<String, Object>());
		eMap.get(eName).put("velocity", eVelocity);
		eMap.get(eName).put("absBearing", eAbsBearing);
		eMap.get(eName).put("pos", ePos);
		eMap.get(eName).put("distance", eDistance);

	}
*/
	
	/*
	 * Perfect lock for a single target
	 */
	
	/*
	public void lockRadar(ScannedRobotEvent e) {
		target = MyUtils.getPos(Data.rPos, Data.eAbsBearing, eDistance);
		//double turn = e.getBearingRadians() + r.getHeadingRadians() - r.getRadarHeadingRadians();
		double turn = Data.eAbsBearing - r.getRadarHeadingRadians();
		System.out.println(e.getBearingRadians() + r.getHeadingRadians() +"/"+ Data.eAbsBearing);
		//double turn = eAbsBearing - r.getRadarHeadingRadians();
		r.setTurnRadarRightRadians(Utils.normalRelativeAngle(turn));
	}
	*/
	
	/*
	 * Melee Radar. Sweeps to oldest target
	 */
	/*
	public void sweepRadar(ScannedRobotEvent e) {
		if (eMap.size() == r.getOthers()) {
			Entry<String, HashMap<String, Object>> map = eMap.entrySet().iterator().next();
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
		Point2D.Double source = new Point2D.Double(r.getX(), r.getY());
		Point2D.Double target = new Point2D.Double(r.getBattleFieldWidth() / 2, r.getBattleFieldHeight() / 2);
		return Utils.normalRelativeAngle(MyUtils.getAbsBearing(source, target) - r.getRadarHeadingRadians());
	}
	 */
}