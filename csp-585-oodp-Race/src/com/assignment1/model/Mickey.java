package com.assignment1.model;

import java.awt.Color;
import java.awt.Graphics;

public class Mickey extends Shape {
	
	private boolean smiling = true;
	public Mickey(String name){
		this.shapeName = name;
	}

	public void drawShape(Graphics g) {
		int startX = position.x;    int startY = position.y;
		
		g.setColor(Color.MAGENTA);
        g.fillArc(startX-5,startY-10+ (int)getTrackCovered(),70,70,0, -180);
        g.setColor(Color.red);
        g.fillRect(startX+5, startY+35+(int)getTrackCovered(), 5, 5);
        g.fillRect(startX+45, startY+39+(int)getTrackCovered(), 5, 5);
        g.fillRect(startX+35, startY+44+(int)getTrackCovered(), 5, 5);
        g.fillRect(startX+20, startY+47+(int)getTrackCovered(), 5, 5);
        g.fillRect(startX+15, startY+35+(int)getTrackCovered(), 5, 5);
        g.fillRect(startX+25, startY+37+(int)getTrackCovered(), 5, 5);
        g.fillRect(startX+10, startY+28+(int)getTrackCovered(), 5, 5);
        
        /*g.setColor(Color.black);
        g.fillOval(startX + (int)(0.2*50),startY+ (int)getTrackCovered() + (int)(0.2*50),
                (int)(0.3*50), (int)(0.3*50));
        g.fillOval(startX + (int)(0.6*50),startY+ (int)getTrackCovered() + (int)(0.2*50),
                (int)(0.3*50), (int)(0.3*50));*/
   
        g.setColor(Color.red);
        g.fillOval(startX + (int)(0.2325*50),startY+10+ (int)getTrackCovered() + (int)(0.2*50+0.0325*50),
                (int)(0.225*50), (int)(0.225*50));
        g.fillOval(startX + (int)(0.6325*50),startY+10+ (int)getTrackCovered() + (int)(0.2*50+0.0325*50),
                (int)(0.225*50), (int)(0.225*50));
        
        g.setColor(Color.blue);
        g.fillOval(startX + (int)(0.2725*50),startY+10+ (int)getTrackCovered() + (int)(0.2*50+0.0725*50),
                (int)(0.125*50), (int)(0.125*50));
        g.fillOval(startX + (int)(0.6725*50),startY+10+ (int)getTrackCovered() + (int)(0.2*50+0.0725*50),
                (int)(0.125*50), (int)(0.125*50));
        
        g.setColor(Color.orange);
        if(smiling)
            g.fillArc(startX+(int)(0.2*50),startY+12+ (int)getTrackCovered()+(int)(0.5*50),
                    (int)(0.6*50), (int)(0.3*50), 0, -180);
        else
             g.fillArc(startX+(int)(0.2*50),startY +12+ (int)getTrackCovered() +(int)(0.5*50),
                    (int)(0.6*50), (int)(0.3*50), 0, 180);
	}
}