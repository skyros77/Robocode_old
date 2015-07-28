package mo;

import mo.Radar.*;
import mo.Data.*;
import robocode.*;
import java.awt.Graphics2D;

public class PewPew extends AdvancedRobot {
	Data data;

	public void run() {
		data = new Data(this);
		Data.setBotNum(getOthers());
		while (true) {
			setTurnRadarRightRadians(Radar.getRadarDir() * Double.POSITIVE_INFINITY);
			scan();
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
