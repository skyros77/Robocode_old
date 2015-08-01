package mo.Data;

import java.awt.geom.Point2D;

import mo.Utils.MyUtils;
import robocode.AdvancedRobot;
import robocode.util.Utils;

public class Move {

	// VARIABLES
	private static AdvancedRobot r;
	Point2D.Double pos;
	Point2D.Double centre;
	Point2D.Double target;
	double absBearing;
	double distance = 1;
	double turn;
	double angle;

	// CONSTRUCTORS
	public Move(AdvancedRobot robot) {
		r = robot;
	}

	// METHODS
	public void update() {

		// basic circle
		pos = new Point2D.Double(r.getX(), r.getY());
		target = new Point2D.Double(r.getBattleFieldWidth() / 2, r.getBattleFieldHeight() / 2);
		distance = pos.distance(target);		
		absBearing = MyUtils.getAbsBearing(pos, target);
		turn = (distance>100) ? -0.2 : 0.2;
		angle = Utils.normalRelativeAngle(absBearing - r.getHeadingRadians()  + turn);		
		r.setTurnRightRadians(angle + Math.PI/2);
		r.setAhead(125);
		
		
		

	}
}
