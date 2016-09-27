package com.assignment1.model;

import java.awt.Color;
import java.awt.Graphics;

public class Goofy extends Shape {
	private boolean smiling = true;
	public Goofy(String name){
		this.shapeName = name;
	}

	public void drawShape(Graphics g) {
		int startX = position.x;    int startY = position.y;
		g.setColor(Color.CYAN);
        
		 g.fillArc(startX-10,startY+20+ (int)getTrackCovered(),75,75,0, 180);
		 g.setColor(Color.orange);
		 g.fillOval(startX+15, startY+25+(int)getTrackCovered(), 4, 4);
	        g.fillOval(startX+35, startY+25+(int)getTrackCovered(), 4, 4);
	        
	        g.fillOval(startX+5, startY+25+(int)getTrackCovered(), 4, 4);
	         
	        g.fillOval(startX+35, startY+25+(int)getTrackCovered(), 4, 4);
	       
	        g.fillOval(startX+5, startY+35+(int)getTrackCovered(), 4, 4);
	       
	        g.fillOval(startX+25, startY+35+(int)getTrackCovered(), 4, 4);
	        
	        g.fillOval(startX+45, startY+35+(int)getTrackCovered(), 4, 4);
	       
	        g.fillOval(startX+15, startY+45+(int)getTrackCovered(), 4, 4);
	      
	        g.fillOval(startX+35, startY+45+(int)getTrackCovered(), 4, 4);
	        
	       
	      
	       
        g.setColor(Color.white);
        g.fillOval(startX + (int)(0.2*50),startY+15+ (int)getTrackCovered() + (int)(0.2*50),
                (int)(0.3*50), (int)(0.3*50));
        g.fillOval(startX + (int)(0.6*50),startY+15+(int)getTrackCovered() + (int)(0.2*50),
                (int)(0.3*50), (int)(0.3*50));
   
        g.setColor(Color.orange);
        g.fillOval(startX + (int)(0.2325*50),startY+15+ (int)getTrackCovered() + (int)(0.2*50+0.0325*50),
                (int)(0.225*50), (int)(0.225*50));
        g.fillOval(startX + (int)(0.6325*50),startY+15+ (int)getTrackCovered() + (int)(0.2*50+0.0325*50),
                (int)(0.225*50), (int)(0.225*50));
        
        g.setColor(Color.black);
        g.fillOval(startX + (int)(0.2725*50),startY+15+ (int)getTrackCovered() + (int)(0.2*50+0.0725*50),
                (int)(0.125*50), (int)(0.125*50));
        g.fillOval(startX + (int)(0.6725*50),startY+15+ (int)getTrackCovered() + (int)(0.2*50+0.0725*50),
                (int)(0.125*50), (int)(0.125*50));
        g.setColor(Color.red);
        if(smiling)
            g.fillArc(startX+(int)(0.2*50),startY+15+ (int)getTrackCovered()+(int)(0.5*50),
                    (int)(0.6*50), (int)(0.3*50), 0, -180);
        else
             g.fillArc(startX+(int)(0.2*50),startY+15+ (int)getTrackCovered()+(int)(0.5*50),
                    (int)(0.6*50), (int)(0.3*50), 0, 180);
	}

}