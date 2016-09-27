package com.assignment4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;




public class DashboardFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private ActionPanel actionPanel;
	private TrackPanel	trackPanel;

	private List<Shape> shapesList;
	private List<String> shapesName;
	public int distanceToTravel;
	
	private int upperY = 80;
	private int lowerY = 620;
	
	protected DashboardFrame(RaceMain raceMain,List<String> nameList) throws IOException {
		super("The Great Race");
		shapesList = raceMain.getShapesList();
		shapesName = nameList;
		this.createPanels();
		
		/* 
		 * 70 distance of starting line from top
		 * 95 distance of ending line from bottom
		 */
		int trackPanelHeight = this.trackPanel.getHeight();
		distanceToTravel = trackPanelHeight - upperY - (trackPanelHeight - lowerY);
	}
	
	private void createPanels(){
		actionPanel = new ActionPanel(this);
		trackPanel = new TrackPanel(this);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(actionPanel, BorderLayout.NORTH);
 		panel.add(trackPanel, BorderLayout.CENTER);
     	
 		this.setSize(800, 715);
     	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
     	this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
            	disposeFrame();
                System.exit(0);
            }
        });
     	
     	this.setLocationRelativeTo(null);
     	this.add(panel);
     	this.setResizable(false);
     	this.setVisible(true);
    }
	
	public void drawFigures(Graphics gp) {
		drawLine(gp, lowerY, 750);
		drawLine(gp, upperY, 750);
		
		for(Shape shape : this.shapesList){
			shape.drawShape(gp);
		}
	}
	
	public void WriteName(Graphics g) {
		int xposition = 85;
		int yposition =20;
		g.setColor(Color.YELLOW);
		g.setFont(new Font("TimesRoman", Font.BOLD, 14));
		for(String str : shapesName){
		g.drawString(str, xposition, yposition + 7);	
		xposition = xposition+60 + 50;
		}
	}
	
	
	private void drawLine(Graphics gp, int y, int width) {
		gp.setColor(Color.BLACK);
		gp.fillRect(20, y, width, 5);
	}
	
	public void updatePosition(long timeElapsed){
		for(Shape shape: this.shapesList){
			shape.reposition(timeElapsed);
		}
	}

	public TrackPanel getTrackPanel(){
		return this.trackPanel;
	}

	public ActionPanel getActionPanel(){
		return this.actionPanel;
	}
	
	private void disposeFrame(){
		this.dispose();
	}
	
	public int getDistanceToTravel(){
		return this.distanceToTravel;
	}
	
	public void reset(){
		for(Shape shape: this.shapesList){
			shape.setTrackCovered(0);
		}
	}
	
	public void updateSpeed(){
		for(Shape shape: this.shapesList){
			shape.setSpeed();
		}
	}
	
	public synchronized Shape getWinner(){
		for(Shape shape: this.shapesList){
			if(shape.getTrackCovered() >= distanceToTravel){
				return shape;
			}
		}
		return null;
	}
	
	public void updateSmile(Shape winner){
		for(Shape shape : this.shapesList){
			if(shape != winner){
				shape.setSmiling(false);
			}
		}
	}
	
	public void setAllSmiling(){
		for(Shape shape : this.shapesList){
			shape.setSmiling(true);
		}
	}
	
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
