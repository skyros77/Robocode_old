package mo.Paint;

import robocode.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.*;

import mo.Data.Gun;
import mo.Data.Radar;

public class Paint {

	/*
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
		
		//PAINT STUFF
		targetPos(Radar.getRobotPos(),Radar.getRadarTarget());
		paintPredictions();
		
		
	}

	public static void targetPos(Point2D.Double source,Point2D.Double target) {
		g.setColor(new Color(255, 0, 0, 100));
		g.fillOval((int) target.x - 8, (int) target.y - 8, 16, 16);
		g.drawLine((int) source.x, (int) source.y, (int) target.x, (int) target.y);
	}	
	
	public static void paintPredictions() {
		try {
			g.setColor(new Color(255,0,0,100));
			ListIterator<Double> i = Gun.getPredictions().listIterator();
			while(i.hasNext()) {
				g.fillOval((int) i.next().x - 2, (int) i.next().y - 2, 4, 4);
			}		
		} catch(NoSuchElementException e) {
			//System.out.println("paintPredictions: " +e);
		}
	}
	 */
}
