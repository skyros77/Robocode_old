package mo.Data;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.LinkedHashMap;

import mo.Paint.*;
import mo.Utils.MyUtils;
import robocode.*;

public class Data {

	// VARIABLES
	protected static AdvancedRobot r;

	protected static Point2D.Double rPos;
	protected static double rRadarHeading;
	protected static double rHeading;
	protected static double rGunHeading;
	protected static double rVelocity;

	protected static Point2D.Double ePos;
	protected static String eName;
	protected static double eEnergy;
	protected static double eVelocity;
	protected static double eBearing;
	protected static double eAbsBearing;
	protected static double eHeading;
	protected static double eDistance;
	protected static double eScore;	
	protected static LinkedHashMap<String, HashMap<String, Object>> eMap = new LinkedHashMap<String, HashMap<String, Object>>(4, 0.75f, true);

	protected static int numBots;
	protected static long round;
	protected static double buffer;
	protected static Rectangle2D.Double field;
	protected static Point2D.Double centre;

	protected static Radar radar;
	protected static Gun gun;
	protected static Move move;
	protected static Paint paint;
	
	// CONSTRUCTORS
	public Data() {
	}

	//initialize variables
	public Data(AdvancedRobot robot) {
		r = robot;
		
		buffer = 18;
		field = new Rectangle2D.Double(buffer, buffer, r.getBattleFieldWidth()-(buffer*2), r.getBattleFieldHeight()-(buffer*2));
		centre = new Point2D.Double(field.width/2, field.height/2);
		
		rPos = new Point2D.Double(r.getX(), r.getY());
		rRadarHeading = r.getRadarHeadingRadians();
		rHeading = r.getHeadingRadians();
		rGunHeading = r.getGunHeadingRadians();
		numBots = r.getOthers();

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
		paint.update(g);
	}

	// update variables
	private void updateVars(ScannedRobotEvent e) {
		round = r.getTime();
		numBots = r.getOthers();

		rPos = new Point2D.Double(r.getX(), r.getY());
		rRadarHeading = r.getRadarHeadingRadians();
		rHeading = r.getHeadingRadians();
		rGunHeading = r.getGunHeadingRadians();
		rVelocity = r.getVelocity();

		eName = e.getName();
		eEnergy = e.getEnergy();
		eVelocity = e.getVelocity();
		eBearing = e.getBearingRadians();
		eDistance = e.getDistance();
		eAbsBearing = e.getBearingRadians() + r.getHeadingRadians();
		eHeading = e.getHeadingRadians();
		ePos = MyUtils.getPos(new Point2D.Double(r.getX(), r.getY()), eAbsBearing, eDistance);
		eScore = setScore();
	}

	// calculate target probability
	public double setScore() {
		double score = 0;
		//target energy level
		score = Math.pow(1-(eEnergy/100),0.2);
		//target distance
		score += MyUtils.clampRange(1-MyUtils.normalizeRange(eDistance, 150, 400),0,1);
		System.out.println(score);
		return score;
	}
	
	// save enemy information
	public void updateMap() {
		eMap.put(eName, new HashMap<String, Object>());
		eMap.get(eName).put("eVelocity", eVelocity);
		eMap.get(eName).put("eHeading", eHeading);
		eMap.get(eName).put("eAbsBearing", eAbsBearing);
		eMap.get(eName).put("ePos", ePos);
		eMap.get(eName).put("eDistance", eDistance);
	}
}
