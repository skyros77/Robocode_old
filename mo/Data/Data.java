package mo.Data;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.LinkedHashMap;

import mo.Paint.*;
import mo.Utils.MyUtils;
import robocode.*;

public class Data {

	// VARIABLES
	private static Radar radar;
	private static Gun gun;
	private static Move move;
	private static Paint paint;

	private static AdvancedRobot r;
	private static Point2D.Double field;
	private static Point2D.Double centre;
	private static Point2D.Double rPos;
	private static double rRadarHeading;
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

	// CONSTRUCTORS
	public Data() {
	}

	public Data(AdvancedRobot robot) {
		// initialize robot variables
		set_robot(robot);
		set_field(new Point2D.Double(r.getBattleFieldWidth(), r.getBattleFieldHeight()));
		set_centre(new Point2D.Double(r.getBattleFieldWidth() / 2, r.getBattleFieldHeight() / 2));
		set_rPos(new Point2D.Double(r.getX(), r.getY()));
		set_rRadarHeading(r.getRadarHeadingRadians());
		set_rHeading(r.getHeadingRadians());
		set_rGunHeading(r.getGunHeadingRadians());
		set_rVelocity(r.getVelocity());
		set_rNum(r.getOthers());

		radar = new Radar();
		gun = new Gun();
		move = new Move(r);
		paint = new Paint();
	}

	// METHODS
	public void update(ScannedRobotEvent e) {
		updateVars(e);
		updateMap();

		radar.update();
		gun.update();
		move.update(e);
	}

	public void update(RobotDeathEvent e) {
		eMap.remove(e.getName());
	}

	public void update(Graphics2D g) {
		// paint.update(g);
	}

	// update variables
	private void updateVars(ScannedRobotEvent e) {

		// my robot
		set_rPos(new Point2D.Double(r.getX(), r.getY()));
		set_rRadarHeading(r.getRadarHeadingRadians());
		set_rHeading(r.getHeadingRadians());
		set_rGunHeading(r.getGunHeadingRadians());
		set_rVelocity(r.getVelocity());
		set_rNum(r.getOthers());

		// enemy robot
		set_eName(e.getName());
		set_eEnergy(e.getEnergy());
		set_eVelocity(e.getVelocity());
		set_eBearing(e.getBearingRadians());
		set_eDistance(e.getDistance());
		set_eAbsBearing(e.getBearingRadians() + r.getHeadingRadians());
		set_eHeading(e.getHeadingRadians());
		set_ePos(MyUtils.getPos(new Point2D.Double(r.getX(), r.getY()), eAbsBearing, eDistance));
	}

	// save enemy information
	public void updateMap() {
		eMap.put(eName, new HashMap<String, Object>());
		eMap.get(eName).put("velocity", get_eVelocity());
		eMap.get(eName).put("heading", get_eHeading());
		eMap.get(eName).put("absBearing", get_eAbsBearing());
		eMap.get(eName).put("pos", get_ePos());
		eMap.get(eName).put("distance", get_eDistance());
	}

	// ACCESSORS & MUTATORS

	public static AdvancedRobot get_robot() {
		return r;
	}

	public static void set_robot(AdvancedRobot v) {
		r = v;
	}

	public static Point2D.Double get_rPos() {
		return rPos;
	}

	public static void set_rPos(Point2D.Double v) {
		rPos = v;
	}

	public static double get_eBearing() {
		return eBearing;
	}

	public static void set_eBearing(double v) {
		eBearing = v;
	}

	public static String get_eName() {
		return eName;
	}

	public static void set_eName(String v) {
		eName = v;
	}

	public static double get_eEnergy() {
		return eEnergy;
	}

	public static void set_eEnergy(double v) {
		eEnergy = v;
	}

	public static double get_eVelocity() {
		return eVelocity;
	}

	public static void set_eVelocity(double v) {
		eVelocity = v;
	}

	public static double get_eDistance() {
		return eDistance;
	}

	public static void set_eDistance(double v) {
		eDistance = v;
	}

	public static double get_eAbsBearing() {
		return eAbsBearing;
	}

	public static void set_eAbsBearing(double v) {
		eAbsBearing = v;
	}

	public static Point2D.Double get_ePos() {
		return ePos;
	}

	public static void set_ePos(Point2D.Double v) {
		ePos = v;
	}

	public static double get_eHeading() {
		return eHeading;
	}

	public static void set_eHeading(double v) {
		eHeading = v;
	}

	public static LinkedHashMap<String, HashMap<String, Object>> get_eMap() {
		return eMap;
	}

	public static Point2D.Double get_field() {
		return field;
	}

	public static void set_field(Point2D.Double v) {
		field = v;
	}

	public static Point2D.Double get_centre() {
		return centre;
	}

	public static void set_centre(Point2D.Double v) {
		centre = v;
	}

	public static double get_rRadarHeading() {
		return rRadarHeading;
	}

	public static void set_rRadarHeading(double v) {
		rRadarHeading = v;
	}

	public static double get_rHeading() {
		return rHeading;
	}

	public static void set_rHeading(double v) {
		rHeading = v;
	}

	public static double get_rGunHeading() {
		return rGunHeading;
	}

	public static void set_rGunHeading(double v) {
		rGunHeading = v;
	}

	public static double get_rVelocity() {
		return rVelocity;
	}

	public static void set_rVelocity(double v) {
		rVelocity = v;
	}

	public static int get_rNum() {
		return rNum;
	}

	public static void set_rNum(int v) {
		rNum = v;
	}
}
