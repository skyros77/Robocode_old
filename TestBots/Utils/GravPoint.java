package TestBots.Utils;

import java.awt.geom.Point2D;

public class GravPoint {
	// VARIABLES
	private Point2D.Double pos;
	private double dist;
	private double weight;

	//CONSTRUCTORS
	public GravPoint() {
	}

	public GravPoint(Point2D.Double p, double d, double w) {
		this.setPos(p);
		this.setDist(d);
		this.setWeight(w);
	}
	//METHODS

	public Point2D.Double getPos() {
		return pos;
	}

	public void setPos(Point2D.Double pos) {
		this.pos = pos;
	}

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	//ACCESSORS
	
	
	

}