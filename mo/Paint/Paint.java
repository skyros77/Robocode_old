package mo.Paint;

import mo.Data.*;

import java.awt.Color;
import java.awt.Graphics2D;

public class Paint {

	// VARIABLES

	// CONSTRUCTOR

	// METHODS
	public static void enPos(Graphics2D g) {
		g.setColor(new Color(255, 0, 0, 200));
		g.fillOval((int) (Data.getPos().x - 10), (int) (Data.getPos().y - 10), 20, 20);
		g.drawLine((int) Data.getMyPos().x, (int) Data.getMyPos().y, (int) Data.getPos().x, (int) Data.getPos().y);
	}
}
