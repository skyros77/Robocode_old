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
	private static AdvancedRobot myBot;		
	private static Radar radar;
	private static Move move;
	private static Paint paint;	

	//general data
	private static int botNum;
	private static Point2D.Double field;
	private static Point2D.Double fieldCentre;	
	
	//my data

	private static Point2D.Double myPos;
	
	//enemy data
	private static String name;
	private static Point2D.Double pos;
	private static double bearing;
	private static double absBearing;
	private static double distance;
	private static double velocity;

	private static LinkedHashMap<String, HashMap<String, Object>> map = new LinkedHashMap<String, HashMap<String, Object>>(4, 0.75f, true);

	
	// CONSTRUCTORS
	public Data(AdvancedRobot robot) {
		myBot 		= robot;				
		radar		= new Radar();		
		move		= new Move();
		paint		= new Paint();			
	
		botNum 		= robot.getOthers();
		field		= new Point2D.Double(robot.getBattleFieldWidth(),robot.getBattleFieldHeight());
		fieldCentre	= new Point2D.Double(field.x/2,field.y/2);		
	}
	
	// METHODS
	public void update(ScannedRobotEvent e) {
		setVars(e);
		setMap();
		
		//update robot
		move.update();		
		radar.update(e);
	
	}

	public void update(RobotDeathEvent e) {
		map.remove(e.getName());
	}

	public void update(Graphics2D g) {
		paint.update(g);
	}
	

	//update variable information
	public void setVars(ScannedRobotEvent e) {
		//my data
		myPos		= new Point2D.Double(myBot.getX(), myBot.getY());
		botNum		= myBot.getOthers();	
		
		//enemy data
		name		= e.getName();
		bearing		= e.getBearingRadians();
		absBearing	= e.getBearingRadians() + myBot.getHeadingRadians();
		distance	= e.getDistance();
		velocity	= e.getVelocity();
		pos			= MyUtils.getPos(myPos, absBearing, distance);		
	}	

	public void setMap() {
		// Add enemy information to Map
		map.put(name, new HashMap<String, Object>());
		map.get(name).put("velocity", velocity);
		map.get(name).put("absBearing", absBearing);
		map.get(name).put("pos", pos);
	}
	
	// ACCESSORS
	public static String getEnName() {
		return name;
	}

	public static Point2D.Double getField() {
		return field;
	}
	
	public static Point2D.Double getFieldCentre() {
		return fieldCentre;
	}
	
	public static Point2D.Double getMyPos() {
		return myPos;
	}

	public static AdvancedRobot getMyBot() {
		return myBot;
	}
	
	public static Point2D.Double getEnPos() {
		return pos;
	}

	public static double getEnBearing() {
		return bearing;
	}

	public static double getEnAbsBearing() {
		return absBearing;
	}

	public static double getEnDistance() {
		return distance;
	}

	public static double getEnVelocity() {
		return velocity;
	}

	public static int getBotNum() {
		return botNum;
	}

	public static LinkedHashMap<String, HashMap<String, Object>> getEnMap() {
		return map;
	}

}