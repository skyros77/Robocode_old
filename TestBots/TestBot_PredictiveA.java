package TestBots;
import robocode.*;
import robocode.util.*;
import java.awt.geom.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics2D;

public class TestBot_PredictiveA extends AdvancedRobot
{

	private static final double	FIRE_POWER		= 0.01;
	private static final double	FIRE_SPEED		= Rules.getBulletSpeed(FIRE_POWER); //20 - (3 * power)
	private static List<Point2D.Double>	predictions	= new ArrayList<Point2D.Double>();
	private static List<Point2D.Double>	turnArc	= new ArrayList<Point2D.Double>();
	double pHeading;
	private static Point2D.Double enemyPos, myPos;
	private static Rectangle2D.Double field;

	public void run()
	{
		field = new Rectangle2D.Double(18,18,getBattleFieldWidth()-36,getBattleFieldHeight()-36);
		setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);	
		turnRadarLeftRadians(Double.POSITIVE_INFINITY);
		while (true) {scan();}
	}


	public void onScannedRobot(ScannedRobotEvent e)
	{
		//clear
		predictions.clear();
		turnArc.clear();
		
		//scan
 	    setTurnRadarRightRadians(Utils.normalRelativeAngle(getHeadingRadians()+e.getBearingRadians()-getRadarHeadingRadians()));

		//get positions
		myPos = new Point2D.Double(getX(),getY());
		enemyPos = getPos(myPos,e.getBearingRadians()+getHeadingRadians(),e.getDistance());

		double cHeading = e.getHeadingRadians();
		double diff = cHeading-pHeading;

		for (int i=0; i<myPos.distance(enemyPos)/FIRE_SPEED; i++)
		{		
			cHeading += diff;
			enemyPos = getPos(enemyPos, diff+cHeading, e.getVelocity());
			predictions.add(enemyPos);			
		}
		
		//turn arc testing
		//max v =8 max turn =10
		//Point2D.Double ePos = getPos(myPos,e.getBearingRadians()+getHeadingRadians(),e.getDistance());
		//Point2D.Double ePos =  new Point2D.Double(getBattleFieldWidth()/2,getBattleFieldHeight()/2);
		Point2D.Double ePos =  new Point2D.Double(0,0);
		double eHeading = 0;
		for (int i=0; i<18; i++)
		{
			out.println("(" + i + ") " + ePos);
			turnArc.add(ePos);
			eHeading += Math.toRadians(10);
			ePos = getPos(ePos,eHeading,10);	

//3.14/2 = 1.57
//0.174532925
				
		}

		pHeading = e.getHeadingRadians();
		

		//turn gun
		double absoluteBearing = Math.atan2(enemyPos.x - myPos.x, enemyPos.y - myPos.y);
		double gunTurn = absoluteBearing - getGunHeadingRadians();
		setTurnGunRightRadians(Utils.normalRelativeAngle(gunTurn));
		
		//fire gun
		if (getGunTurnRemainingRadians()<.1)  
			setFire(FIRE_POWER);
			

		
	}

	public void onPaint(Graphics2D g) {	
		

		g.setColor(new Color(255,0,0,255));
		g.drawLine((int)52,(int)0,(int)52,(int)52);
		
		g.setColor(new Color(255,0,0,255));
		g.fillOval((int)enemyPos.x-3, (int)enemyPos.y-3, 6, 6);
		
		g.setColor(new Color(255,0,0,100));
		g.drawLine((int)enemyPos.x,(int)enemyPos.y,(int)myPos.x,(int)myPos.y);
		
/*
		g.setColor(new Color(255,0,0,100));
		//for (Point2D.Double p : predictions)
		for (int i=0; i<predictions.size()-1; i++)
		{
			predictions.get(i);
			g.drawLine((int)predictions.get(i).x,(int)predictions.get(i).y,(int)predictions.get(i+1).x,(int)predictions.get(i+1).y);
		}
*/
		g.setColor(Color.WHITE);
		for (int i=0; i<turnArc.size(); i++)
		{
			turnArc.get(i);
			g.drawLine((int)turnArc.get(i).x,(int)turnArc.get(i).y,(int)turnArc.get(i+1).x,(int)turnArc.get(i+1).y);
		}		


		g.setColor(new Color(0,255,0,50));
		g.drawRect((int)field.x, (int)field.y, (int)field.width, (int)field.height);
	}

	private static Point2D.Double getPos(Point2D.Double pos, double angle, double distance)
	{
		
		double x = pos.x + distance * Math.sin(angle);
		double y = pos.y + distance * Math.cos(angle);
		
/*
		if (x<field.x)		x = field.x;
		if (x>field.width)	x = field.width+18;
		if (y<field.y)		y = field.y;
		if (y>field.height)	y = field.height+18;
				
*/
		return new Point2D.Double(x, y);
	}
}