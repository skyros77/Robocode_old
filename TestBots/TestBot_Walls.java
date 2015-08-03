package TestBots;
import robocode.Robot;

public class TestBot_Walls extends Robot {

	double moveAmount;

	public void run() {
		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		turnLeft(getHeading() % 90);
		ahead(moveAmount);
		turnGunRight(90);
		turnRight(90);

		while (true) {
			ahead(moveAmount);
			turnRight(90);
		}
	}
}
