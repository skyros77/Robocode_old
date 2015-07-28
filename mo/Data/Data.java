package mo.Data;
import java.awt.geom.*;
import robocode.*;

public class Data {

	//VARIABLES
	private AdvancedRobot myBot;
	private ScannedRobotEvent enemy;
	
	private static Point2D.Double myPos;
	private static String enName;	
	
	
	
	// CONSTRUCTORS
	public Data(AdvancedRobot e) {
		this.myBot = e;
	}
	public Data(ScannedRobotEvent e) {
		this.enemy = e;
	}

	//METHODS
	public void update(ScannedRobotEvent e) {
		myPos	= new Point2D.Double(myBot.getX(),myBot.getY());
		enName	= e.getName();			
	}
	
	// ASSESSORS
	public static Point2D.Double getMyPos() {
		return myPos;
	}
	
	public static String getEnName() {
		return enName;
	}	
}
