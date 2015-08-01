package mo.Data;

import java.awt.Graphics2D;
import java.awt.geom.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import mo.Utils.*;
import mo.Paint.Paint;
import robocode.*;

public class Data {

	// VARIABLES
	private static AdvancedRobot r;
	private static Radar radar;
	private static Gun gun;
	private static Move move;
	private static Paint paint;

	// robot data
	private static Point2D.Double myPos;

	// enemy data
	private static String name;
	private static Point2D.Double pos;
	private static double absBearing;
	private static double distance;
	private static double velocity;

	private static LinkedHashMap<String, HashMap<String, Object>> map = new LinkedHashMap<String, HashMap<String, Object>>(4, 0.75f, true);

	// CONSTRUCTORS
	public Data(AdvancedRobot robot) {
		r = robot;
		radar = new Radar(r);
		gun = new Gun(r);
		move = new Move(r);
		paint = new Paint(r);
	}

	// METHODS
	public void update(ScannedRobotEvent e) {

		// update robot
		radar.update(e);
		gun.update(e);
		move.update();

		setVars(e);
		setMap(e);
		
		
		///* ATTEMPTING TO FIND IF ENEMY IS FACING MY BOT - FAILED
		double absBearing2 = MyUtils.getAbsBearing(myPos,pos);
		double enmBearing =  e.getHeadingRadians()-Math.PI;
		double danger = enmBearing - absBearing2;
		System.out.println(
			"enmBearing: " + enmBearing +"\n"+
			"absBearing: " + absBearing2 +"\n"+
			"diff: " + (enmBearing/absBearing2) +"\n"
				);

		 
	}

	public void update(RobotDeathEvent e) {
		map.remove(e.getName());
	}

	public void update(Graphics2D g) {
		paint.update(g);
	}

	// update variable information
	public void setVars(ScannedRobotEvent e) {
		// my data
		myPos = new Point2D.Double(r.getX(), r.getY());

		// enemy data
		name = e.getName();
		absBearing = e.getBearingRadians() + r.getHeadingRadians();
		distance = e.getDistance();
		velocity = e.getVelocity();
		pos = MyUtils.getPos(myPos, absBearing, distance);
	}

	public void setMap(ScannedRobotEvent e) {
		// Add enemy information to Map
		map.put(name, new HashMap<String, Object>());
		map.get(name).put("velocity", velocity);
		map.get(name).put("absBearing", e.getBearingRadians() + r.getHeadingRadians());
		map.get(name).put("pos", pos);
		map.get(name).put("distance", distance);

	}

	// ACCESSORS
	public static LinkedHashMap<String, HashMap<String, Object>> eMap() {
		return map;
	}
}