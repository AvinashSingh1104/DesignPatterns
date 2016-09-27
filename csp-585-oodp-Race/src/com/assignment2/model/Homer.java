package com.assignment2.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Homer extends Shape{
	
	private boolean smiling = true;
	private int numCols = 6;
	
		public Homer(String name){
			this.shapeName = name;
		}

		public void drawShape(Graphics g) {
			int startX = position.x;    int startY = position.y;
			g.setColor(Color.pink);
	        
	        g.fillRoundRect(startX,startY+10+ (int)getTrackCovered(),80,50,50,40);
	        
	        //hands
	        if(!((int)getTrackCovered()%30==0 || (int)getTrackCovered()%30==1||(int)getTrackCovered()%30==2||(int)getTrackCovered()%30==3)){
	        g.fillRoundRect(startX-13,startY+40+ (int)getTrackCovered(),17,10,45,40);
	        g.fillRoundRect(startX+75,startY+40+ (int)getTrackCovered(),17,10,45,40);
	        }
	        g.setColor(Color.yellow);
	        g.fillRoundRect(startX+5,startY+15 +(int)getTrackCovered(),70,40,40,30);
	        g.setColor(Color.white);
	        g.fillOval(startX + (int)(0.2*80),startY+ 10+(int)getTrackCovered() + (int)(0.2*50),
	                (int)(0.3*80), (int)(0.3*50));
	        g.fillOval(startX + (int)(0.6*80),startY+ 10+(int)getTrackCovered() + (int)(0.2*50),
	                (int)(0.3*80), (int)(0.3*50));
	   
	        g.setColor(Color.LIGHT_GRAY);
	        g.fillOval(startX + (int)(0.2325*80),startY+ 10+(int)getTrackCovered() + (int)(0.2*50+0.0325*50),
	                (int)(0.225*80), (int)(0.225*50));
	        g.fillOval(startX + (int)(0.6325*80),startY+ 10+(int)getTrackCovered() + (int)(0.2*50+0.0325*50),
	                (int)(0.225*80), (int)(0.225*50));
	        
	        g.setColor(new Color(randcolor1(),randcolor1(),0));
	        g.fillOval(startX + (int)(0.2725*80),startY+ 10+(int)getTrackCovered() + (int)(0.2*50+0.0725*50),
	                (int)(0.125*80), (int)(0.125*50));
	        g.fillOval(startX + (int)(0.6725*80),startY+ 10+(int)getTrackCovered() + (int)(0.2*50+0.0725*50),
	                (int)(0.125*80), (int)(0.125*50));
	        
	        
	        
	        g.setColor(Color.pink);
	        if(smiling)
	            g.fillArc(startX+(int)(0.2*80),startY+10+ (int)getTrackCovered()+(int)(0.5*50),
	                    (int)(0.6*80), (int)(0.3*50), 0, -180);
	        else
	             g.fillArc(startX+(int)(0.2*80),startY+10+ (int)getTrackCovered()+(int)(0.5*50),
	                    (int)(0.6*80), (int)(0.3*50), 0, 180);

		}
		
		private int randcolor1() {
	        int color;
	        Random randomNumber = new Random();
	        color = randomNumber.nextInt(255);
	        return color;
	    }

}