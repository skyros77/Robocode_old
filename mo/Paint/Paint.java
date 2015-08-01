package mo.Paint;

import robocode.AdvancedRobot;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import mo.Data.Radar;

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
		targetPos(Radar.getRadarTargetPos());
	}

	public static void targetPos(Point2D.Double target) {
		g.setColor(new Color(255, 0, 0, 100));
		g.fillOval((int) target.x - 10, (int) target.y - 10, 20, 20);
		g.drawLine((int) r.getX(), (int) r.getY(), (int) target.x, (int) target.y);
	}
}
