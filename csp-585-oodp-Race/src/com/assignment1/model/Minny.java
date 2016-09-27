
package com.assignment1.model;

import java.awt.Color;
import java.awt.Graphics;

public class Minny extends Shape  {
	
	
	public Minny(String name){
		this.shapeName = name;
	}

	public void drawShape(Graphics g) {
		int startX = position.x;    int startY = position.y;
		g.setColor(Color.red);
        
        g.fillOval(startX,startY+10+ (int)getTrackCovered() ,50,50);
        /*g.setColor(Color.DARK_GRAY);
        g.fillOval(startX + (int)(0.2*50),startY+ (int)getTrackCovered() + (int)(0.2*50),
                (int)(0.3*50), (int)(0.3*50));
        g.fillOval(startX + (int)(0.6*50),startY + (int)getTrackCovered()  + (int)(0.2*50),
                (int)(0.3*50), (int)(0.3*50));*/
   
        g.setColor(Color.cyan);
        g.fillRect(startX + (int)(0.2325*50),startY+10+ (int)getTrackCovered() + (int)(0.2*50+0.0325*50),
                (int)(0.225*50), (int)(0.225*50));
        g.fillRect(startX + (int)(0.6325*50),startY+10+ (int)getTrackCovered() + (int)(0.2*50+0.0325*50),
                (int)(0.225*50), (int)(0.225*50));
        
        g.setColor(Color.black);
        g.fillOval(startX + (int)(0.2725*50),startY+10+ (int)getTrackCovered() + (int)(0.2*50+0.0725*50),
                (int)(0.125*50), (int)(0.125*50));
        g.fillOval(startX + (int)(0.6725*50),startY+10+ (int)getTrackCovered() + (int)(0.2*50+0.0725*50),
                (int)(0.125*50), (int)(0.125*50));
        
        g.setColor(Color.BLUE);
       
            g.fillOval(startX+3+
            		(int)(0.2*50),startY+ 12+ (int)getTrackCovered()+(int)(0.5*50),
                    (int)(0.5*50), (int)(0.2*50));
  
	}

}