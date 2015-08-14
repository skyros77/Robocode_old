package mo.Data;

import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.geom.Point2D.Double;
import mo.Utils.*;
import mo.Paint.*;
import robocode.*;

public class Data {

	// VARIABLES
	private static AdvancedRobot r;
	private static Radar radar;
	private static Gun gun;
	private static Move move;
	private static Paint paint;

	// ROBOT DATA
	private static Point2D.Double rPos;
	private static double rHeading;


	// ENEMY DATA
	private static String eName;
	private static double eEnergy;
	private static double eVelocity;
	private static double eBearing;
	private static double eAbsBearing;
	private static double eDistance;
	private static Point2D.Double ePos;

	// GENERAL
	private static Point2D.Double field;
	private static Point2D.Double fieldCentre;
	private static double robotNum;

	// CONSTRUCTORS
	public Data() {}
	
	public Data(AdvancedRobot robot) {
		r = robot;
		radar = new Radar(r);
		gun = new Gun(r);
		move = new Move(r);

		/*
		field = new Double(r.getBattleFieldWidth(), r.getBattleFieldHeight());
		fieldCentre = new Double(r.getBattleFieldWidth() / 2, r.getBattleFieldHeight() / 2);
		*/
	}

	// METHODS
	public void update(ScannedRobotEvent e) {
		radar.update(e);
		/*
		radar.update(e);
		gun.update(e);
		move.update(e);
		*/
		setVars(e);
	}

	public void update(RobotDeathEvent e) {
	}

	public void update(Graphics2D g) {
		//paint.update(g);
	}

	// update variable information
	public void setVars(ScannedRobotEvent e) {
		// ROBOT
		rPos = new Point2D.Double(r.getX(), r.getY());
		rHeading = r.getHeadingRadians();

		// ENEMY
		eName = e.getName();
		eEnergy = e.getEnergy();
		eVelocity = e.getVelocity();
		eBearing = e.getBearingRadians();
		eDistance = e.getDistance();
		eAbsBearing = eBearing + rHeading;
		ePos = MyUtils.getPos(rPos, eAbsBearing, eDistance);
		
		// GENERAL
		robotNum = r.getOthers();

	}

	/*
	 * GETTERS
	 */

	// ROBOT
	public static Double get_rPos() {
		return rPos;
	}

	public static double get_rHeading() {
		return rHeading;
	}

	// ENEMY
	public static String get_eName() {
		return eName;
	}

	public static double get_eBearing() {
		return eBearing;
	}

	public static double get_eVelocity() {
		return eVelocity;
	}

	public static double get_eAbsBearing() {
		return eAbsBearing;
	}

	public static double get_eDistance() {
		return eDistance;
	}

	public static Double get_ePos() {
		return ePos;
	}

	public static Double get_field() {
		return field;
	}

	// GENERAL
	public static double get_robotNum() {
		return robotNum;
	}
	
	/*
	 * SETTERS
	 */

	// ROBOT
	public void set_rPos(Point2D.Double rPos) {
		this.rPos = rPos;
	}

	public void set_rHeading(double rHeading) {
		this.rHeading = rHeading;
	}

	// ENEMY
	public void set_eName(String eName) {
		this.eName = eName;
	}

	public void set_eBearing(double eBearing) {
		this.eBearing = eBearing;
	}

	public void set_eVelocity(double eVelocity) {
		this.eVelocity = eVelocity;
	}

	public void set_eAbsBearing(double eAbsBearing) {
		this.eAbsBearing = eAbsBearing;
	}

	public void set_eDistance(double eDistance) {
		this.eDistance = eDistance;
	}

	public void set_ePos(Point2D.Double ePos) {
		this.ePos = ePos;
	}

	public void set_field(Point2D.Double field) {
		this.field = field;
	}
}
