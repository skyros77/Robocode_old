package mo;

import mo.Data.*;
import robocode.*;
import java.awt.Graphics2D;

public class PewPew extends AdvancedRobot {
	Data data;
	public void run() {
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		setAdjustRadarForRobotTurn(true);		
		
		data = new Data(this);
		while (true) {
			setTurnRadarRightRadians(Radar.getRadarDir() * Double.POSITIVE_INFINITY);
			execute();
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		data.update(e);	
	}

	public void onRobotDeath(RobotDeathEvent e) {
		data.update(e);
	}

	public void onPaint(Graphics2D g) {
		data.update(g);
	}
}