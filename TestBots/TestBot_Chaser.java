package TestBots;

import java.awt.geom.Point2D;

import mo.Utils.MyUtils;
import robocode.AdvancedRobot;
import robocode.Rules;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

public class TestBot_Chaser extends AdvancedRobot {

	private static double firePower;
	private static double fireSpeed;
	private static double pHeading;

	public void run() {
		setAdjustRadarForRobotTurn(true);
		while (true)
			turnRadarRightRadians(Double.POSITIVE_INFINITY);
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		setTurnRadarRightRadians(Utils.normalRelativeAngle(getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians()));
		setTurnRightRadians(Utils.normalRelativeAngle(getHeadingRadians() + e.getBearingRadians() - getHeadingRadians()));
		setAhead(100);
		fireGun(e);
	}

	public void fireGun(ScannedRobotEvent e) {
		firePower = e.getEnergy() / getEnergy();
		fireSpeed = Rules.getBulletSpeed(firePower);

		Point2D.Double rPos = new Point2D.Double(getX(), getY());
		Point2D.Double ePos = MyUtils.getPos(rPos, e.getBearingRadians() + getHeadingRadians(), e.getDistance());
		double cHeading = e.getHeadingRadians();
		double diff = cHeading - pHeading;

		for (int i = 0; i < rPos.distance(ePos) / fireSpeed; i++) {
			cHeading += diff;
			ePos = MyUtils.getPos(ePos, cHeading + diff, e.getVelocity());
		}

		pHeading = e.getHeadingRadians();

		// turn gun
		double absBearing = Math.atan2(ePos.x - rPos.x, ePos.y - rPos.y);
		setTurnGunRightRadians(Utils.normalRelativeAngle(absBearing - getGunHeadingRadians()));

		// fire gun
		if (getGunTurnRemainingRadians() < .1) {
			setFire(firePower);
		}
	}
}