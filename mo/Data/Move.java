package mo.Data;

import java.awt.geom.Point2D;

import mo.Utils.*;
import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

public class Move {


	// VARIABLES
	private static AdvancedRobot r;
	private static Point2D.Double myPos;
	private static Point2D.Double ePos;	
	private static int direction = 1;
	private static double absBearing;
	private static double turn;	
	private static double distance;

	
	// CONSTRUCTORS
	public Move(AdvancedRobot robot) {
		r = robot;
	}

	// METHODS
	public void update(ScannedRobotEvent e) {
		
		/*
		//basic circle
		pos = new Point2D.Double(r.getX(), r.getY());
		target = new Point2D.Double(r.getBattleFieldWidth() / 2, r.getBattleFieldHeight() / 2);
		distance = pos.distance(target);		
		absBearing = MyUtils.getAbsBearing(pos, target);
		turn = (distance>100) ? -0.2 : 0.2;
		angle = Utils.normalRelativeAngle(absBearing - r.getHeadingRadians()  + turn);		
		r.setTurnRightRadians(angle + Math.PI/2);
		r.setAhead(125);
		*/
				
		/*
		if (r.getTime() % 8 == 0) {
			int num = MyUtils.randomNum(-1,1);
			if (num!=0) direction *= num;
		}
		
		distance = 200/e.getDistance() < 1 ? 1 : -1;

		r.setAhead(100 * direction);
		r.setTurnRightRadians(Utils.normalRelativeAngle(e.getBearingRadians() + Math.PI/2 - (distance*direction)));
		*/
	
		myPos = new Point2D.Double(r.getX(),r.getY());
		ePos = MyUtils.getPos(myPos, e.getBearingRadians() + r.getHeadingRadians(), e.getDistance());
		absBearing = MyUtils.getAbsBearing(myPos, ePos);
		double range = MyUtils.clampRange(MyUtils.normalizeRange(e.getDistance(), 250, 350),-1,1);
		//double clamp = MyUtils.clampRange(distance, -1, 1);
		
		
		
		
		turn = Utils.normalRelativeAngle(absBearing-r.getHeadingRadians()) > 0 ? 1 : -1;
		turn *= (Math.PI/2)*distance;
		System.out.println(turn);
		
		
		r.setAhead(100);		
		r.setTurnRightRadians(Utils.normalRelativeAngle(e.getBearingRadians() + Math.PI/2));			
		//r.setTurnRightRadians(Utils.normalRelativeAngle(e.getBearingRadians() + turn));		
		
	}

}
