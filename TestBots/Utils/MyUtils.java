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
	
	//ACCESSORS
}
