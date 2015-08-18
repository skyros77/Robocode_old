package TestBots.Utils;

import java.awt.geom.Point2D;
import java.util.*;

import robocode.util.Utils;

public class GravManager {
	// VARIABLES
	private Vector gravPoints = new Vector();
	private double pointForce = 0;
	private double fallOff = 2;
	private double xForce = 0.0;
	private double yForce = 0.0;

	// CONSTRUCTORS
	public GravManager() {
	}

	// METHODS
	public void addPoint(GravPoint g) {
		gravPoints.add(g);
	}

	public long getNumPoints() {
		return gravPoints.size();
	}
	
	public boolean removePoint(GravPoint g) {
		return gravPoints.remove(g);
	}
	
	public void resetPoints() {
		gravPoints = new Vector();
	}

	
	public void update(Point2D.Double pos) {
		xForce = 0.0;
		yForce = 0.0;
		GravPoint g;
		double force;
		double angle;
		for(int i=0;i<gravPoints.size();i++) {
			g = (GravPoint) gravPoints.elementAt(i);
			force = g.getWeight()/Math.pow(pos.distance(g.getPos()),fallOff);		
			angle = MyUtils.getAbsBearing(pos, g.getPos());
			xForce += force * Math.sin(angle);
			yForce += force * Math.cos(angle);
		}
	}
	
	
	
	// GETTERS & SETTERS
	public Vector get_gravPoints() {
		return gravPoints;
	}

	public void set_gravPoints(Vector gravPoints) {
		this.gravPoints = gravPoints;
	}
	
	public double get_PointForce() {
		return pointForce;
	}

	public void set_PointForce(double pointForce) {
		this.pointForce = pointForce;
	}
	
	public double get_fallOff() {
		return fallOff;
	}

	public void setfallOff(double fallOff) {
		this.fallOff = fallOff;
	}
}