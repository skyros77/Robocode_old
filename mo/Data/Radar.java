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
	private static AdvancedRobot r;
	private static Point2D.Double rPos;
	private static double rRadarHeading;
	private static double rRadarDir;
	private static double rHeading;
	private static double rGunHeading;
	private static double rVelocity;
	private static int rNum;
	private static Point2D.Double ePos;
	private static String eName;
	private static double eEnergy;
	private static double eVelocity;
	private static double eBearing;
	private static double eAbsBearing;
	private static double eHeading;
	private static double eDistance;
	private static LinkedHashMap<String, HashMap<String, Object>> eMap = new LinkedHashMap<String, HashMap<String, Object>>(4, 0.75f, true);

	private static Point2D.Double field;
	private static Point2D.Double centre;
	// CONSTRUCTOR
	public Radar() {
	}

	public Radar(AdvancedRobot robot) {
		r = robot;
	}

	// METHODS
	public void update(ScannedRobotEvent e) {
		updateVars(e);
		updateMap();

		if (rNum == 1) 
			lockRadar();
		else 
			sweepRadar();

	}

	public void update(RobotDeathEvent e) {
		// remove enemies from map on death
		eMap.remove(e.getName());
	}

	// update variables
	private void updateVars(ScannedRobotEvent e) {
		set_rPos(new Point2D.Double(r.getX(), r.getY()));
		set_rNum(r.getOthers());
		set_rHeading(r.getHeadingRadians());
		set_rRadarHeading(r.getRadarHeadingRadians());
		set_rGunHeading(r.getGunHeadingRadians());
		set_rVelocity(r.getVelocity());
		set_eName(e.getName());
		set_eEnergy(e.getEnergy());
		set_eVelocity(e.getVelocity());
		set_eBearing(e.getBearingRadians());
		set_eDistance(e.getDistance());
		set_eAbsBearing(e.getBearingRadians() + r.getHeadingRadians());
		set_eHeading(e.getHeadingRadians());
		set_ePos(MyUtils.getPos(rPos, eAbsBearing, eDistance));
		set_rRadarDir(get_RadarDir(rPos, ePos));
		
		set_field(new Point2D.Double(r.getBattleFieldWidth(),r.getBattleFieldHeight()));
		set_centre(new Point2D.Double(r.getBattleFieldWidth()/2,r.getBattleFieldHeight()/2));
	}

	// save enemy information
	public void updateMap() {
		eMap.put(eName, new HashMap<String, Object>());
		eMap.get(eName).put("velocity", eVelocity);
		eMap.get(eName).put("absBearing", eAbsBearing);
		eMap.get(eName).put("pos", ePos);
		eMap.get(eName).put("distance", eDistance);
	}

	/*
	 * single target radar lock
	 */
	public void lockRadar() {
		rRadarDir = Utils.normalRelativeAngle(eAbsBearing - rRadarHeading);
		r.setTurnRadarRightRadians(Utils.normalRelativeAngle(rRadarDir));
		//Paint.targetPos(ePos);
	}

	/*
	 * Melee Radar. Sweeps to oldest target
	 */
	public void sweepRadar() {
		if (eMap.size() == rNum) {
			Entry<String, HashMap<String, Object>> map = eMap.entrySet().iterator().next();
			eAbsBearing = (Double) map.getValue().get("absBearing");
			rRadarDir = Utils.normalRelativeAngle(eAbsBearing - rRadarHeading);
			r.setTurnRadarRightRadians(rRadarDir * Double.POSITIVE_INFINITY);
			//Paint.targetPos(ePos);
		}
	}

	public static double get_RadarDir() {
		Point2D.Double source = new Point2D.Double(r.getX(), r.getY());
		Point2D.Double target = new Point2D.Double(r.getBattleFieldWidth() / 2, r.getBattleFieldHeight() / 2);
		return get_RadarDir(source, target);
	}

	public static double get_RadarDir(Point2D.Double source, Point2D.Double target) {
		return Utils.normalRelativeAngle(MyUtils.getAbsBearing(source, target) - r.getRadarHeadingRadians());
	}

	// GETTERS & SETTERS
	public static Point2D.Double get_rPos() {
		return rPos;
	}

	public static void set_rPos(Point2D.Double rPos) {
		Radar.rPos = rPos;
	}

	public static double get_eBearing() {
		return eBearing;
	}

	public static void set_eBearing(double eBearing) {
		Radar.eBearing = eBearing;
	}

	public static double get_rHeading() {
		return rHeading;
	}

	public static void set_rHeading(double rHeading) {
		Radar.rHeading = rHeading;
	}

	public static double get_rRadarHeading() {
		return rRadarHeading;
	}

	public static void set_rRadarHeading(double rRadarHeading) {
		Radar.rRadarHeading = rRadarHeading;
	}

	public static int get_rNum() {
		return rNum;
	}

	public static void set_rNum(int rNum) {
		Radar.rNum = rNum;
	}

	public static String get_eName() {
		return eName;
	}

	public static void set_eName(String eName) {
		Radar.eName = eName;
	}

	public static double get_eEnergy() {
		return eEnergy;
	}

	public static void set_eEnergy(double eEnergy) {
		Radar.eEnergy = eEnergy;
	}

	public static double get_eVelocity() {
		return eVelocity;
	}

	public static void set_eVelocity(double eVelocity) {
		Radar.eVelocity = eVelocity;
	}

	public static double get_eDistance() {
		return eDistance;
	}

	public static void set_eDistance(double eDistance) {
		Radar.eDistance = eDistance;
	}

	public static double get_eAbsBearing() {
		return eAbsBearing;
	}

	public static void set_eAbsBearing(double eAbsBearing) {
		Radar.eAbsBearing = eAbsBearing;
	}

	public static Point2D.Double get_ePos() {
		return ePos;
	}

	public static void set_ePos(Point2D.Double ePos) {
		Radar.ePos = ePos;
	}

	public static double get_rRadarDir() {
		return rRadarDir;
	}

	public static void set_rRadarDir(double rRadarDir) {
		Radar.rRadarDir = rRadarDir;
	}

	public static double get_eHeading() {
		return eHeading;
	}

	public static void set_eHeading(double eHeading) {
		Radar.eHeading = eHeading;
	}

	public static double get_rGunHeading() {
		return rGunHeading;
	}

	public static void set_rGunHeading(double rGunHeading) {
		Radar.rGunHeading = rGunHeading;
	}
	
	public static double get_rVelocity() {
		return rVelocity;
	}

	public static void set_rVelocity(double rVelocity) {
		Radar.rVelocity = rVelocity;
	}
	
	public static Point2D.Double get_field() {
		return field;
	}

	public static void set_field(Point2D.Double field) {
		Radar.field = field;
	}
	public static Point2D.Double get_centre() {
		return centre;
	}

	public static void set_centre(Point2D.Double centre) {
		Radar.centre = centre;
	}
}