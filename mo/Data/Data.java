package mo.Data;

import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.geom.Point2D.Double;
import mo.Utils.*;
import mo.Paint.Paint;
import robocode.*;

public class Data {

	// VARIABLES
	protected static AdvancedRobot r;
	protected static Radar radar;
	protected static Gun gun;
	protected static Move move;
	protected static Paint paint;

	// ROBOT DATA
	protected static Point2D.Double rPos;

	// ENEMY DATA
	protected static String eName;
	protected static double eVelocity;
	protected static double eAbsBearing;
	protected static double eDistance;
	protected static Point2D.Double ePos;
	protected static double poop;	

	// CONSTANT DATA
	protected static Point2D.Double field;
	protected static Point2D.Double fieldCentre;	
	

	// CONSTRUCTORS
	public Data() {}
	
	public Data(AdvancedRobot robot) {
		r = robot;
		radar = new Radar(r);
		gun = new Gun(r);
		move = new Move(r);
		paint = new Paint(r);
		
		field = new Double(r.getBattleFieldWidth(), r.getBattleFieldHeight());
		fieldCentre = new Double(r.getBattleFieldWidth()/2, r.getBattleFieldHeight()/2);		
	}

	// METHODS
	public void update(ScannedRobotEvent e) {
		// update robot
		setVars(e);		
		radar.update(e);
		gun.update(e);
		move.update(e);

	}

	public void update(RobotDeathEvent e) {
	}

	public void update(Graphics2D g) {
		paint.update(g);
	}

	// update variable information
	public void setVars(ScannedRobotEvent e) {
		// my data
		rPos = new Point2D.Double(r.getX(), r.getY());

		// enemy data
		eName = e.getName();
		eVelocity = e.getVelocity();
		eAbsBearing = e.getBearingRadians() + r.getHeadingRadians();
		eDistance = e.getDistance();
		ePos = MyUtils.getPos(rPos, eAbsBearing, eDistance);
	}

	// GETTERS
	public static String eName() {
		return eName;
	}

	public static double eVelocity() {
		return eVelocity;
	}

	public static double eAbsBearing() {
		return eAbsBearing;
	}

	public static double eDistance() {
		return eDistance;
	}

	public static Double ePos() {
		return ePos;
	}

	public static Double field() {
		return field;
	}

	// SETTERS

}
