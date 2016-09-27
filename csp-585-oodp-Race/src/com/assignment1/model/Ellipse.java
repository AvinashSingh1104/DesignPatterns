package com.assignment1.model;

import java.awt.Color;
import java.awt.Graphics;

public class Ellipse extends Shape {
	
	private boolean smiling = true;
	public Ellipse(String name){
		this.shapeName = name;
	}

	public void drawShape(Graphics g) {
		int startX = position.x;    int startY = position.y;
		g.setColor(Color.orange);
        
        g.fill3DRect(startX+5,startY+10+ (int)getTrackCovered(),45,50,true);
        g.setColor(Color.yellow);
        g.fillOval(startX+15, startY+10+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+25, startY+10+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+35, startY+10+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+45, startY+10+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+5, startY+15+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+15, startY+15+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+25, startY+15+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+35, startY+15+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+45, startY+15+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+5, startY+25+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+15, startY+25+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+25, startY+25+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+35, startY+25+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+45, startY+25+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+5, startY+35+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+15, startY+35+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+25, startY+35+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+35, startY+35+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+45, startY+35+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+5, startY+45+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+15, startY+45+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+25, startY+45+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+35, startY+45+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+45, startY+45+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+5, startY+55+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+15, startY+55+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+25, startY+55+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+35, startY+55+(int)getTrackCovered(), 4, 4);
        g.fillOval(startX+45, startY+55+(int)getTrackCovered(), 4, 4);
        
        g.setColor(Color.white);
        g.fillOval(startX + (int)(0.2*50),startY+ (int)getTrackCovered() + (int)(0.2*50),
                (int)(0.3*50), (int)(0.3*50));
        g.fillOval(startX + (int)(0.6*50),startY+ (int)getTrackCovered() + (int)(0.2*50),
                (int)(0.3*50), (int)(0.3*50));
   
        g.setColor(Color.DARK_GRAY);
        g.fillOval(startX + (int)(0.2325*50),startY+ (int)getTrackCovered() + (int)(0.2*50+0.0325*50),
                (int)(0.225*50), (int)(0.225*50));
        g.fillOval(startX + (int)(0.6325*50),startY+ (int)getTrackCovered() + (int)(0.2*50+0.0325*50),
                (int)(0.225*50), (int)(0.225*50));
        
        /*g.setColor(Color.black);
        g.fillOval(startX + (int)(0.2725*50),startY+ (int)getTrackCovered() + (int)(0.2*50+0.0725*50),
                (int)(0.125*50), (int)(0.125*50));
        g.fillOval(startX + (int)(0.6725*50),startY+ (int)getTrackCovered() + (int)(0.2*50+0.0725*50),
                (int)(0.125*50), (int)(0.125*50));*/
        g.setColor(Color.green);
        if(smiling)
            g.fillArc(startX+(int)(0.2*50),startY+ (int)getTrackCovered()+(int)(0.5*50),
                    (int)(0.6*50), (int)(0.3*50), 0, -180);
        else
             g.fillArc(startX+(int)(0.2*50),startY+ (int)getTrackCovered()+(int)(0.5*50),
                    (int)(0.6*50), (int)(0.3*50), 0, 180);
	}

}