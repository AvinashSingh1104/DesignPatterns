package com.assignment2.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class SpongeBob extends Shape{

	private boolean smiling = true;
	public SpongeBob(String name){
		this.shapeName = name;
	}

	public void drawShape(Graphics g){
		int startX = position.x;    int startY = position.y;
		g.setColor(Color.yellow);
        
        g.fillRoundRect(startX,startY+10+ (int)getTrackCovered(),50,50,10,10);
        
        //hands
        if(!((int)getTrackCovered()%30==0 || (int)getTrackCovered()%30==1||(int)getTrackCovered()%30==2||(int)getTrackCovered()%30==3)){
        g.fillRoundRect(startX-16,startY+40+ (int)getTrackCovered(),17,10,10,10);
        g.fillRoundRect(startX+49,startY+40+ (int)getTrackCovered(),17,10,10,10);
        }
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(startX+5, startY+15+(int)getTrackCovered(), 8, 8);
        g.fillOval(startX+15, startY+15+(int)getTrackCovered(), 8, 8);
        g.fillOval(startX+30, startY+15+(int)getTrackCovered(), 8, 8);
  
        g.fillOval(startX+5, startY+15+(int)getTrackCovered(), 8, 8);
        g.fillOval(startX+5, startY+25+(int)getTrackCovered(), 8, 8);
        g.fillOval(startX+10, startY+30+(int)getTrackCovered(), 8, 8);
        
        
      
        g.setColor(Color.white);
        g.fillOval(startX + (int)(0.2*50),startY+10+ (int)getTrackCovered() + (int)(0.2*50),
                (int)(0.3*50), (int)(0.3*50));
        g.fillOval(startX + (int)(0.6*50),startY+10+ (int)getTrackCovered() + (int)(0.2*50),
                (int)(0.3*50), (int)(0.3*50));
   
        g.setColor(Color.blue);
        g.fillOval(startX + (int)(0.2325*50),startY+10+ (int)getTrackCovered() + (int)(0.2*50+0.0325*50),
                (int)(0.225*50), (int)(0.225*50));
        g.fillOval(startX + (int)(0.6325*50),startY+10+ (int)getTrackCovered() + (int)(0.2*50+0.0325*50),
                (int)(0.225*50), (int)(0.225*50));
        
         
        g.setColor(new Color(randcolor(),0,0));
        g.fillOval(startX + (int)(0.2725*50),startY+10+ (int)getTrackCovered() + (int)(0.2*50+0.0725*50),
                (int)(0.125*50), (int)(0.125*50));
        g.fillOval(startX + (int)(0.6725*50),startY+10+ (int)getTrackCovered() + (int)(0.2*50+0.0725*50),
                (int)(0.125*50), (int)(0.125*50));
        g.setColor(Color.DARK_GRAY);
        if(smiling)
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