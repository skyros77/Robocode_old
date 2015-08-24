package mo.Paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.*;

import mo.Data.*;

public class Paint{

	// VARIABLES
	private static Graphics2D g;

	// CONSTRUCTOR
	public Paint() {
	}

	// METHODS
	public void update(Graphics2D gfx) {
		g = gfx;
	}

	public static void targetPos(Point2D.Double target) {
		try {
			g.setColor(new Color(255, 0, 0, 255));
			g.drawOval((int) target.x - 30, (int) target.y - 30, 60, 60);
		} catch (NullPointerException e) {
		}
	}

	public static void predictions(List<Point2D.Double> predictions) {
		try {
			g.setColor(new Color(255, 0, 0, 100));
			for (Point2D.Double p : predictions)
				g.fillOval((int) p.x - 2, (int) p.y - 2, 4, 4);
		} catch (NullPointerException e) {
		}
	}

	public static void line(Point2D.Double source, Point2D.Double target) {
		g.setColor(new Color(0, 0, 255, 255));
		g.drawLine((int) source.x, (int) source.y, (int) target.x, (int) target.y);
	}

	public static void oval(Point2D.Double source, double radius) {
		g.setColor(new Color(0, 0, 255, 255));
		g.drawOval((int) (source.x - radius / 2), (int) (source.y - radius / 2), (int) radius, (int) radius);
	}

	public static void rectangle(Rectangle2D.Double r) {
		g.setColor(new Color(0, 0, 255, 255));
		g.drawRect((int) r.x, (int) r.y, (int) r.width, (int) r.height);
	}

}
