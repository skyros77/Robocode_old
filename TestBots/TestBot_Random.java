package TestBots;

import mo.Utils.MyUtils;
import robocode.*;

public class TestBot_Random extends AdvancedRobot {

	double dir = 1;
	double turn = 1;

	public void run() {
		while (true) {
			if (getTime() % MyUtils.randomNum(30, 70) == 0) dir *= -1;
			if (getTime() % MyUtils.randomNum(30, 70) == 0) turn *= -1;

			setAhead(100 * dir);
			setTurnRightRadians(turn * Double.POSITIVE_INFINITY);
			execute();
		}
	}

	public void onHitWall(HitWallEvent event) {
		dir *= -1;
	}
	
	public void onHitRobot(HitRobotEvent event) {
		dir *= -1;
	}

}
