package mo.Data;

import java.awt.Graphics2D;
import mo.Paint.*;
import robocode.*;

public class Data {

	// VARIABLES
	private static AdvancedRobot r;
	private static Radar radar;
	private static Gun gun;
	private static Move move;
	private static Paint paint;

	// CONSTRUCTORS
	public Data() {}
	
	public Data(AdvancedRobot robot) {
		r 	= robot;
		radar 	= new Radar(r);
		gun 	= new Gun(r);
		move 	= new Move(r);
		paint 	= new Paint();
	}

	// METHODS
	public void update(ScannedRobotEvent e) {
		radar.update(e);
		gun.update(e);
		move.update(e);
	}

	public void update(RobotDeathEvent e) {
		radar.update(e);	
	}

	public void update(Graphics2D g) {
		paint.update(g);
	}
}
