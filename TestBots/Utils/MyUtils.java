package TestBots.Utils;

import java.awt.geom.Point2D;

public class MyUtils {
	//VARIABLES
	
	//CONSTRUCTORS
	
	//METHODS
	
	// absolute bearing between two points
	public static double getAbsBearing(Point2D.Double source, Point2D.Double target) {
		return Math.atan2(target.x - source.x, target.y - source.y);
	}
	
	// get X/Y position of a target
	public static Point2D.Double getPos(Point2D.Double pos, double angle, double distance) {
		double x = pos.x + distance * Math.sin(angle);
		double y = pos.y + distance * Math.cos(angle);
		return new Point2D.Double(x, y);
	}
	
	//ACCESSORS
}
