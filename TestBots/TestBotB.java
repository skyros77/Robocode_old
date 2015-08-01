package TestBots;

import java.awt.geom.Point2D;
import robocode.*;
import robocode.util.Utils;

public class TestBotB extends AdvancedRobot {
	Point2D.Double pos;
	Point2D.Double centre;
	Point2D.Double target;
	double absBearing;
	double distance = 1;
	double turn;

	public void run() {
		while (true) {
			if (distance > 0) {
				pos = new Point2D.Double(getX(), getY());
				target = new Point2D.Double(getBattleFieldWidth() / 2, getBattleFieldHeight() / 2);
				absBearing = BotUtils.getAbsBearing(pos, target);
				turn = Utils.normalRelativeAngle(absBearing - getHeadingRadians());
				distance = pos.distance(target);
				turnRightRadians(turn);
				ahead(distance);
			} else
				turnRightRadians(Double.NEGATIVE_INFINITY);
		}
	}
}
