package mo.Data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import robocode.ScannedRobotEvent;

public class RadarData {

	// VARIABLES	
	private BotData myBot;
	private EnemyData enemy;
	private LinkedHashMap<String, HashMap<String, Object>> eMap = enemy.getMap();
	private double radarDir = 1;
	private Entry<String, HashMap<String, Object>> eTarget;
	// private Point2D.Double scanTargetPos;

	// CONSTRUCTORS
	public RadarData() {
	}	
	
	public RadarData(BotData robot) {
		this.myBot = robot;
	}
	
	public RadarData(EnemyData robot) {
		this.enemy = robot;
	}

	// ASSESSORS
	public double getRadarDir() {
		return this.radarDir;
	}

	// METHODS
	public void update(ScannedRobotEvent e) {
		// radar scan for oldest target
		if (eMap.size() == myBot.getOthers()) {
			eTarget = eMap.entrySet().iterator().next();
			//radarDir = Utils.normalRelativeAngle((double)eTarget.getValue().get("eAbsBearing") - myBot.getHeadingRadians());
			//scanTargetPos = (Point2D.Double)scanTarget.getValue().get("ePos"); //get target
		}
	}
}
