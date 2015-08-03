package mo.Paint;

import robocode.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import mo.Data.Gun;

public class Paint {

	// VARIABLES
	private static AdvancedRobot r;
	private static Graphics2D g;

	// CONSTRUCTOR
	public Paint(AdvancedRobot robot) {
		r = robot;
	}

	// METHODS
	public void update(Graphics2D gfx) {
		g = gfx;
	}
	
	public void update(ScannedRobotEvent e) {
	}

	public static void targetPos(Point2D.Double source,Point2D.Double target) {
		g.setColor(new Color(255, 0, 0, 100));
		g.fillOval((int) target.x - 8, (int) target.y - 8, 16, 16);
		g.drawLine((int) source.x, (int) source.y, (int) target.x, (int) target.y);
	}	
	
	public static void predictions() {
		g.setColor(new Color(255,0,0,100));
		System.out.println(Gun.getPredictions());
		//for (Point2D.Double p : predictions)
		/*
		for (int i=0; i<Gun.getPredictions().size()-1; i++)
		{
			Gun.getPredictions().get(i);
			g.drawLine((int)Gun.getPredictions().get(i).x,(int)Gun.getPredictions().get(i).y,(int)Gun.getPredictions().get(i+1).x,(int)Gun.getPredictions().get(i+1).y);
		}
		*/
	}
}
