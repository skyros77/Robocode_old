package mo;

import mo.Data.*;
import robocode.*;
import java.awt.Graphics2D;

public class PewPew extends AdvancedRobot {
	Data data;
	Move move = new Move();
	Radar radar = new Radar();
	public void run() {
		data = new Data(this);
		//Data.setBotNum(getOthers());
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
	
	public void onSkippedTurnEvent(long skippedTurn) {
		System.out.println("SKIPPED TURN!!");
	}
}