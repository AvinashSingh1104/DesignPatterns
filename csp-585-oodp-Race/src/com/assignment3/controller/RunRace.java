package com.assignment3.controller;

import com.assignment3.model.Shape;
import com.assignment3.view.ActionPanel;
import com.assignment3.view.DashboardFrame;
import com.assignment3.view.TrackPanel;

public class RunRace implements Runnable {

	private boolean isRunning = true;
	
	private DashboardFrame mainFrame;
	private TrackPanel trackPanel;
	private ActionPanel actionPanel;
	
	public RunRace(DashboardFrame mainFrame, TrackPanel panel){
		this.mainFrame = mainFrame;
		this.trackPanel = mainFrame.getTrackPanel();
		this.actionPanel = mainFrame.getActionPanel();
	}
	
	@Override
	public void run() {
		long raceStart = System.currentTimeMillis();
		long previous = 0;
		while(isRunning){
			//update speed of each shape in each iteration
			mainFrame.updateSpeed();
			
			//calculate time elapsed since last iteration
			long current = System.currentTimeMillis();
			long timeElapsed = current - raceStart - previous;
			previous += timeElapsed;
			
			//update distance traveled with currect speed and in the elapsed time 
			mainFrame.updatePosition(timeElapsed);
			
			try {
				// sleep to slow down application 
				// use with multiplier as 0.001 in reposition() of shape method
				  
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// reposition all shapes with new coordinates
			trackPanel.repaint();
			
			Shape winner = mainFrame.getWinner();
			if(winner != null){
				actionPanel.getWinnerNameLabel().setText(winner.getName() + " is winner...");
				isRunning = false;
				smileChange(winner);
				bounceTheWinner(winner);
			}
		}
	}
	
	public void setIsRunning(boolean isRunning){
		this.isRunning = isRunning;
	}
	
	public void smileChange(Shape objShape){
		mainFrame.updateSmile(objShape);
	}
	
	public void bounceTheWinner(Shape objShape){
		for(int i=0;i<=4;i++){
			for (int j = 400; j <= 540; j++) {
				objShape.setTrackCovered(j);
				trackPanel.repaint();
				
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					System.out.println("Thread sleep interrupted" + e.getMessage());
				}
				
			}
		}
	}

}