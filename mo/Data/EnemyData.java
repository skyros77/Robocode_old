package mo.Data;

import robocode.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.LinkedHashMap;

import mo.Utils.*;

public class EnemyData {

	// VARIABLES
	private BotData myBot;
	private String name;
	private double absBearing;
	private double bearing;
	private double distance;
	private double velocity;
	private Point2D.Double pos;
	private LinkedHashMap<String, HashMap<String, Object>> map = new LinkedHashMap<String, HashMap<String, Object>>(5, 2, true);

	// CONSTRUCTORS
	public EnemyData(BotData robot) {
		this.myBot = robot;
	}

	// METHODS
	public void update(ScannedRobotEvent e) {
		name		= e.getName();
		distance	= e.getDistance();
		velocity	= e.getVelocity();
		bearing		= e.getBearingRadians();
		absBearing	= myBot.getHeadingRadians() + e.getBearingRadians();	
		pos			= BotUtils.getPos(myBot.getPos(), absBearing, distance);
		
		// Add enemy information to Map
		map.put(name, new HashMap<String, Object>());
		map.get(name).put("velocity", velocity);
		map.get(name).put("absBearing", absBearing);
		map.get(name).put("bearing", bearing);
		map.get(name).put("pos", pos);	
		
		//debug
		//BotUtils.printMap(map);
	}

	public void update(RobotDeathEvent e) {
		map.remove(e.getName());
	}
	
	// ACCESSORS
	public String getName() {
		return this.name;
	}

	public double getAbsBearing() {
		return this.absBearing;
	}

	public double getBearing() {
		return this.bearing;
	}

	public double getDistance() {
		return this.distance;
	}
	
	public double getVelocity() {
		return this.velocity;
	}

	public Point2D.Double getPos() {
		return this.pos;
	}

	public LinkedHashMap<String, HashMap<String, Object>> getMap() {
		return this.map;
	}
}
