package TestBots;

import java.awt.geom.Point2D;
import java.util.Vector;

import TestBots.Utils.GravPoint;
import robocode.*;

public class TestBot_Gravity extends AdvancedRobot {

	private static Point2D.Double field;
	private static Vector gravPoints = new Vector();

    public void run() {
		setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);	
        field = new Point2D.Double(getBattleFieldWidth(),getBattleFieldHeight());
		while(true)	turnRadarLeftRadians(Double.POSITIVE_INFINITY);
	}
    
    public void onScannedRobot(ScannedRobotEvent e) {
		setAhead(100);
    }

	public void addPoint(GravPoint g) {
		gravPoints.add(g);
	}
}