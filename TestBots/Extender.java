package TestBots;

import java.awt.geom.Point2D;

import TestBots.ExtenderB.ExtenderB;
import mo.Utils.MyUtils;
import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

public class Extender extends AdvancedRobot {
	
	//VARIABLES
	ExtenderB data;
	
	protected static Point2D.Double rPos;
	protected static Point2D.Double ePos;
	protected static double eDist;
	private static double eEnergy;	
	
	//METHODS
	public void run() {
		setAdjustRadarForRobotTurn(true);
		data = new ExtenderB(this);		
		while (true) {
			turnRadarRightRadians(Double.POSITIVE_INFINITY);
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		setTurnRadarRightRadians(Utils.normalRelativeAngle(getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians()));
		eDist = e.getDistance();
		rPos = new Point2D.Double(getX(),getY());
		ePos = MyUtils.getPos(rPos, e.getBearingRadians() + getHeadingRadians(), eDist);
		eEnergy = e.getEnergy();
		
		ExtenderB.setGun(e);
	}
}
