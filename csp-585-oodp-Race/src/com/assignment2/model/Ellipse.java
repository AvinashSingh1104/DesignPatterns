package com.assignment2.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ellipse extends Shape {
	
	Thread th=new Thread();
	Color color;
	
	private boolean smiling = true;
	public Ellipse(String name){
		this.shapeName = name;
	}

	public void drawShape(Graphics g) {
		int startX = position.x;    int startY = position.y;
		g.setColor(Color.orange);
        
        g.fill3DRect(startX+5,startY+10+ (int)getTrackCovered(),45,50,true);
        //hands
        if(!((int)getTrackCovered()%30==0 || (int)getTrackCovered()%30==1||(int)getTrackCovered()%30==2||(int)getTrackCovered()%30==3)){
        g.fill3DRect(startX-10,startY+40+(int)getTrackCovered(),16,10,true);
        g.fill3DRect(startX+49,startY+40+(int)getTrackCovered(),17,10,true);
        }
        
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
       
        
        g.setColor(new Color(randcolor()));
        g.fillOval(startX + (int)(0.2325*50),startY+ (int)getTrackCovered() + (int)(0.2*50+0.0325*50),
                (int)(0.225*50), (int)(0.225*50));
        g.fillOval(startX + (int)(0.6325*50),startY+ (int)getTrackCovered() + (int)(0.2*50+0.0325*50),
                (int)(0.225*50), (int)(0.225*50));
       
        g.setColor(Color.green);
        if(smiling)
            g.fillArc(startX+(int)(0.2*50),startY+ (int)getTrackCovered()+(int)(0.5*50),
                    (int)(0.6*50), (int)(0.3*50), 0, -180);
        else
             g.fillArc(startX+(int)(0.2*50),startY+ (int)getTrackCovered()+(int)(0.5*50),
                    (int)(0.6*50), (int)(0.3*50), 0, 180);
        
        
	}
	
	private int randcolor() {
        int color;
        Random randomNumber = new Random();
        color = randomNumber.nextInt(255);
        return color;
    }
	

}