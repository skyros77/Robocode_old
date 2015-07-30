package mo.Paint;

import mo.Data.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Paint {

	// VARIABLES
	private static Graphics2D gfx;

	// CONSTRUCTOR

	// METHODS
	public void update(Graphics2D g) {
		gfx = g;
	}

	public static void targetPos(Point2D.Double target) {
		gfx.setColor(new Color(255, 0, 0, 100));
		gfx.fillOval((int) target.x - 10, (int) target.y - 10, 20, 20);
		gfx.drawLine((int) Data.getMyPos().x, (int) Data.getMyPos().y, (int) target.x, (int) target.y);
	}
}
