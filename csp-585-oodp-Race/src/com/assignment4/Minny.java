
package com.assignment4;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Minny extends Shape  {
	
	protected Minny(String name){
		this.shapeName = name;
	}

	public void drawShape(Graphics g) {
		int startX = position.x;    int startY = position.y;
		g.setColor(Color.red);
        
        g.fillOval(startX,startY+10+ (int)getTrackCovered() ,50,50);
        
        //hands
        if(!((int)getTrackCovered()%30==0 || (int)getTrackCovered()%30==1||(int)getTrackCovered()%30==2||(int)getTrackCovered()%30==3)){
        g.fillOval(startX-10,startY+45+ (int)getTrackCovered() ,17,10);
        g.fillOval(startX+42,startY+45+ (int)getTrackCovered() ,17,10);
        }
       
   
        g.setColor(Color.cyan);
        g.fillRect(startX + (int)(0.2325*50),startY+10+ (int)getTrackCovered() + (int)(0.2*50+0.0325*50),
                (int)(0.225*50), (int)(0.225*50));
        g.fillRect(startX + (int)(0.6325*50),startY+10+ (int)getTrackCovered() + (int)(0.2*50+0.0325*50),
                (int)(0.225*50), (int)(0.225*50));
        
        g.setColor(new Color(0,randcolor(),0));
        g.fillOval(startX + (int)(0.2725*50),startY+10+ (int)getTrackCovered() + (int)(0.2*50+0.0725*50),
                (int)(0.125*50), (int)(0.125*50));
        g.fillOval(startX + (int)(0.6725*50),startY+10+ (int)getTrackCovered() + (int)(0.2*50+0.0725*50),
                (int)(0.125*50), (int)(0.125*50));
        
         
        g.setColor(Color.BLUE);
        if(isSmiling())
            g.fillArc(startX+(int)(0.2*50),startY+10+ (int)getTrackCovered()+(int)(0.5*50),
                    (int)(0.6*50), (int)(0.3*50), 0, -180);
        else
             g.fillArc(startX+(int)(0.2*50),startY+10+ (int)getTrackCovered()+(int)(0.5*50),
                    (int)(0.6*50), (int)(0.3*50), 0, 180);
        
  
	}
	
	private int randcolor() {
        int color;
        Random randomNumber = new Random();
        color = randomNumber.nextInt(255);
        return color;
    }

}