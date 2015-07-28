package mo;  

import java.awt.Graphics2D;
import mo.Data.*;
import robocode.*;

public class PewPew extends AdvancedRobot {

	//BotData myBot;
	//EnemyData enemy;
	//RadarData radar;
	Data data;
	public void run() {
		data = new Data(this);
		//myBot = new BotData(this);
		//enemy = new EnemyData(myBot);
		//radar = new RadarData(this);		
		
		while (true) {	
			setTurnRadarRightRadians(Double.POSITIVE_INFINITY);
			setAhead(100);
			scan();
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		data.update(e);
		Enemy.debug();
		//myBot.update();
		//enemy.update(e);
		//radar.update(e);
	}

	public void onRobotDeath(RobotDeathEvent e) {
		//myBot.update();
		//enemy.update(e);
	}

	public void onPaint(Graphics2D g) {
		//if (enemy.getPos() != null) {
		//	paint.drawPos(g, enemy.getPos());
		//}
	}
}
