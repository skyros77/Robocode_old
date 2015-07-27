package mo;  

import java.awt.Graphics2D;
import mo.Data.*;
import mo.Utils.*;
import robocode.*;

public class PewPew extends AdvancedRobot {

	BotData myBot;
	EnemyData enemy;

	public void run() {
		myBot = new BotData(this);
		enemy = new EnemyData(myBot);
		while (true) {	
			setTurnRadarRightRadians(radar.getRadarDir() * Double.POSITIVE_INFINITY);
			scan();
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		myBot.update();
		enemy.update(e);
		// radar.update(e);
	}

	public void onRobotDeath(RobotDeathEvent e) {
		myBot.update();
		enemy.update(e);
	}

	public void onPaint(Graphics2D g) {
		//if (enemy.getPos() != null) {
		//	paint.drawPos(g, enemy.getPos());
		//}
	}
}
