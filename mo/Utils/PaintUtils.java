package mo.Utils;

import mo.Data.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class PaintUtils {

	// CONSTRUCTOR

	// VARIABLES
	private BotData myBot;	
	private Point2D.Double myPos = myBot.getPos();

	// ACCESSORS

	// METHODS
	public void drawPos(Graphics2D g, Point2D.Double ePos) {
		g.setColor(new Color(255, 0, 0, 200));
		g.fillOval((int) (ePos.x - 10), (int) (ePos.y - 10), 20, 20);
		g.drawLine((int) myPos.x, (int) myPos.y, (int) ePos.x, (int) ePos.y);
	}
	
	//get X/Y position of a target
	public static Point2D.Double getPos(Point2D.Double pos, double angle, double distance) {
		double x = pos.x + distance * Math.sin(angle);
		double y = pos.y + distance * Math.cos(angle);
		return new Point2D.Double(x, y);
	}
	
}
