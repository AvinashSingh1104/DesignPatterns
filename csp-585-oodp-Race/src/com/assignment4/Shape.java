package com.assignment4;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Shape {
	public static final int gapbetweenShapes = 60, shapebreadth = 60, margin = 50;

	protected static int xposition = 25 + gapbetweenShapes, yPosition = 20;

	protected static Point currentPosition = new Point(xposition, yPosition);

	private int speed = 0;
	
	protected String shapeName;

	private double trackCovered = 0;

	protected boolean smiling = true;

	static {
		currentPosition = new Point(xposition, yPosition);
	}

	protected Point position;

	public void setPosition() {
		position = new Point(currentPosition.x, currentPosition.y);
		currentPosition.x += gapbetweenShapes + margin;
	}

	public Shape() {
		setPosition();
	}

	public void setSpeed() {
		speed = ((int) (Math.random() * 6));
	}

	public int getSpeed() {
		return this.speed;
	}

	public double getTrackCovered() {
		return this.trackCovered;
	}

	public void setTrackCovered(int value) {
		trackCovered = value;
	}
	
	public String getName() {
		return this.shapeName;
	}

	public void reposition(long timeElapsed) {
		
		double multiplier = 0.01;
		trackCovered += speed * timeElapsed * multiplier;
	}

	public boolean isSmiling() {
		return smiling;
	}

	public void setSmiling(boolean smiling) {
		this.smiling = smiling;
	}
	
	
	public abstract void drawShape(Graphics grph);
	
}
