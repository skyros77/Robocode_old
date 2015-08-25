package mo.Utils;

import java.awt.geom.Point2D;

public class BotUtils {

	// print out map information
	/*
	public static void printMap(LinkedHashMap<String, HashMap<String, Object>> enemy) {
		for (Entry<String, HashMap<String, Object>> cursor : enemy.entrySet()) {
		}
	}
	*/

	// get X/Y position of a target (normalized)
	public static Point2D.Double getPos(double angle) {
		double x = Math.sin(angle);
		double y = Math.cos(angle);
		return new Point2D.Double(x, y);
	}
	
	// get X/Y position of a target
	public static Point2D.Double getPos(Point2D.Double pos, double angle, double distance) {
		double x = pos.x + distance * Math.sin(angle);
		double y = pos.y + distance * Math.cos(angle);
		return new Point2D.Double(x, y);
	}

	// absolute bearing between two points
	public static double getAbsBearing(Point2D.Double source, Point2D.Double target) {
		return Math.atan2(target.x - source.x, target.y - source.y);
	}

	// dot product
	public static double dot(Point2D.Double v1, Point2D.Double v2) {
		return (v1.x * v2.x) + (v1.y * v2.y);
	}
	
	// get angle between two vectors
	public static double angle(double dot) {
		return Math.acos(dot);
	}
	
	//random number generator
	public static int randomNum(int min, int max)
	{
	   int range = (max - min) + 1;
	   return (int)(Math.random() * range) + min;
	}
	
	//normalize a range of numbers between min/max values
	public static double normalizeRange(double value, double min, double max) {
		return (value-min)/(max-min);
	}
	
	// clamp a range of numbers between min/max values
	public static double clampRange(double value, double min, double max) {
		return Math.max(min,Math.min(value,max));
	}
	
	
	
	
	/*
	 // check if scannedrobot is facing me or not (0-1 range) 
	 // absBearing of target and my bot
	 double targetBearing = MyUtils.getAbsBearing(pos, rPos) + Math.PI; 
	 // get normalized position of robot bearing and target bearing 
	 v1 = MyUtils.getPos(targetBearing); 
	 v2 = MyUtils.getPos(e.getHeadingRadians()); 
	 // dot product of two vectors 
	 double dot = MyUtils.dot(v1, v2);
	 // angle between two vectors 
	 double angle = MyUtils.angle(dot); 
	 // normalize angle into 0-1 range 
	 double danger = angle / Math.PI; 
	 System.out.println(danger);
	 */
	
	
}
