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
	private AdvancedRobot myBot;

	private static String name;
	private static Point2D.Double myPos;
	private static Point2D.Double pos;
	private static double myHeading;
	private static double myRadarHeading;
	private static double bearing;
	private static double absBearing;
	private static double distance;
	private static double velocity;
	private static int botNum;
	private static LinkedHashMap<String, HashMap<String, Object>> map = new LinkedHashMap<String, HashMap<String, Object>>(5, 2, true);

	// CONSTRUCTORS
	public Data(AdvancedRobot e) {
		this.myBot = e;
	}

	// METHODS
	public void update(ScannedRobotEvent e) {
		name = e.getName();
		myPos = new Point2D.Double(myBot.getX(), myBot.getY());
		pos = Utils.getPos(myPos, absBearing, distance);
		myHeading = myBot.getHeadingRadians();
		myRadarHeading = myBot.getRadarHeadingRadians();
		bearing = e.getBearingRadians();
		absBearing = e.getBearingRadians() + myHeading;
		distance = e.getDistance();
		velocity = e.getVelocity();
		setBotNum(myBot.getOthers());

		// Add enemy information to Map
		map.put(name, new HashMap<String, Object>());
		map.get(name).put("velocity", velocity);
		map.get(name).put("absBearing", absBearing);
		map.get(name).put("pos", pos);
	}

	public void update(RobotDeathEvent e) {
		map.remove(e.getName());
	}

	public void update(Graphics2D g) {
		Paint.enPos(g);
	}

	// SETTER
	public static void setBotNum(int botNum) {
		Data.botNum = botNum;
	}

	// GETTER
	public static String getName() {
		return name;
	}

	public static Point2D.Double getMyPos() {
		return myPos;
	}

	public static Point2D.Double getPos() {
		return pos;
	}

	public static double getMyHeading() {
		return myHeading;
	}

	public static double getMyRadarHeading() {
		return myRadarHeading;
	}

	public static double getbearing() {
		return bearing;
	}

	public static double getAbsBearing() {
		return absBearing;
	}

	public static double getDistance() {
		return distance;
	}

	public static double getVelocity() {
		return velocity;
	}

	public static int getBotNum() {
		return botNum;
	}

	public static LinkedHashMap<String, HashMap<String, Object>> getMap() {
		return map;
	}

}
