/* 
 * TO DO
 * Turn radar shortest distance to centre of map
 * Set up radar for single enemy
 * For Melee battles target lock my opponent but occasionally sweep the field
 */

package mo.Data;

import mo.Utils.*;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map.Entry;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;



public class Radar {

	// VARIABLES
	private static AdvancedRobot myBot;
	private static double radarDir = 1;
	private static Point2D.Double radarTargetPos;
	private static double absBearing;

	//CONSTRUCTOR
	public Radar() {
		//setRadarDir(Data.getMyPos(),Data.getFieldCentre());	
	}

	
	// METHODS
	public void update(ScannedRobotEvent e) {
		myBot = Data.getMyBot();
		setRadarDir();		
	}

	public static void setRadarDir() {
		if (Data.getEnMap().size() == Data.getBotNum()) {
			Entry<String, HashMap<String, Object>> map = Data.getEnMap().entrySet().iterator().next();
			absBearing = (Double) map.getValue().get("absBearing");
			radarDir = Utils.normalRelativeAngle(absBearing - myBot.getRadarHeadingRadians());
		}
	}
	
	
	public static void setRadarDir(Point2D source, Point2D target) {
		radarDir = MyUtils.getAbsBearing(source, target);
	}

	// ACCESSORS
	public static Point2D.Double getRadarTargetPos() {
		return radarTargetPos;
	}

	public static double getRadarDir() {
		return radarDir;
	}

}