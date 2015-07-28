package mo.Data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

public class RadarData {

	// VARIABLES	
	private AdvancedRobot myBot;
	private EnemyData enemy;
	private double radarDir = 1;	
	private LinkedHashMap<String, HashMap<String, Object>> map;
	private Entry<String, HashMap<String, Object>> target;
	// private Point2D.Double scanTargetPos;

	// CONSTRUCTORS
	public RadarData() {
	}	
	

	public RadarData(EnemyData robot) {
		this.enemy = robot;
	}
	
	public RadarData(AdvancedRobot robot) {
		this.myBot = robot;
	}

	// ASSESSORS
	public double getRadarDir() {
		return this.radarDir;
	}

	// METHODS
	public void update(ScannedRobotEvent e) {
		// radar scan for oldest target
		//map = enemy.getMap();
		//System.out.println(map);
		/*
		if (map.size() == myBot.getOthers()) {
			target = map.entrySet().iterator().next();
			System.out.println(target);
			radarDir = Utils.normalRelativeAngle((double)eTarget.getValue().get("eAbsBearing") - myBot.getHeadingRadians());
			scanTargetPos = (Point2D.Double)scanTarget.getValue().get("ePos"); //get target
		}
		*/
	}
}
