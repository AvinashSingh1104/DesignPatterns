package com.assignment3.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Shape {

	public static final int gapbetweenShapes = 60, shapebreadth = 60,
			margin = 50;

	protected static int xposition = 25 + gapbetweenShapes, yPosition = 20;

	protected static Point currentPosition = new Point(xposition, yPosition);

	private int speed = 0;

	private double trackCovered = 0;

	protected boolean smiling = true;

	static {
		currentPosition = new Point(xposition, yPosition);
	}

	protected String shapeName;

	public String getName() {
		return this.shapeName;
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

	public void reposition(long timeElapsed) {
		
		double multiplier = 0.01;
		trackCovered += speed * timeElapsed * multiplier;
	}

	public void writeName(Graphics g) {
		g.setColor(Color.YELLOW);
		g.setFont(new Font("TimesRoman", Font.BOLD, 14));
		g.drawString(shapeName, position.x, position.y + 7);
	}

	public boolean isSmiling() {
		return smiling;
	}

	public void setSmiling(boolean smiling) {
		this.smiling = smiling;
	}
	
	public abstract void drawShape(Graphics grph);
}