package TestBots;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import TestBots.Utils.*;
import robocode.*;

public class TestBot_Gravity extends AdvancedRobot {

	// VARIABLES
	private ArrayList<GravPoint> gPt = new ArrayList<GravPoint>();
	private Point2D.Double field;
	private Point2D.Double rPos;

	private Point2D.Double ePos;
	private double eAbsBearing;
	private double eDistance;

	// METHODS
	public void run() {
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		set_field(new Point2D.Double(getBattleFieldWidth(), getBattleFieldHeight()));
		while (true)
			turnRadarLeftRadians(Double.POSITIVE_INFINITY);
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		set_rPos(new Point2D.Double(getX(), getY()));
		set_eDistance(e.getDistance());
		set_ePos(MyUtils.getPos(get_rPos(), e.getBearingRadians() + getHeadingRadians(), get_eDistance()));
		set_eAbsBearing(MyUtils.getAbsBearing(get_rPos(), get_ePos()));

		// add grav point
		addPoint(get_ePos(), get_eDistance(), 2);
	}

	public void addPoint(Point2D.Double pos, double distance, double weight) {
		GravPoint g = new GravPoint(pos, distance, weight);
		gPt.add(g);
	}

	// ACCESSORS
	public Point2D.Double get_rPos() {
		return rPos;
	}

	public void set_rPos(Point2D.Double v) {
		this.rPos = v;
	}

	public Point2D.Double get_ePos() {
		return ePos;
	}

	public void set_ePos(Point2D.Double v) {
		this.ePos = v;
	}

	public double get_eDistance() {
		return eDistance;
	}

	public void set_eDistance(double v) {
		this.eDistance = v;
	}

	public double get_eAbsBearing() {
		return eAbsBearing;
	}

	public void set_eAbsBearing(double v) {
		this.eAbsBearing = v;
	}

	public Point2D.Double get_field() {
		return field;
	}

	public void set_field(Point2D.Double field) {
		this.field = field;
	}
}