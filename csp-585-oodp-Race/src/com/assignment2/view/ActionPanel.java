package com.assignment2.view;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sun.audio.*;

import java.io.*;

import com.assignment2.controller.RunRace;

public class ActionPanel extends JPanel{

	private static final long serialVersionUID = 8549418363445304981L;
	
	private JLabel winnerNameLabel;
	private JButton callButton;
    private JButton raceStartButton;
    
    private DashboardFrame mainFrame;
    
    private RunRace runRace;
    private Thread thread;
    
    public AudioPlayer musicPlayer = AudioPlayer.player;
    public AudioStream backgroundMusic = null;
    public AudioData audioData;	
    public ContinuousAudioDataStream audioStream= null;
    
    public ActionPanel(DashboardFrame mainFrame){
    	this.mainFrame = mainFrame;
    	createPanels();
    }
    
    public void createPanels(){
    	this.setLayout(new GridLayout(1, 3));
    	
    	callButton = new JButton("Call Players Back");
    	//callButton.setLayout(new FlowLayout());
    	callButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	//reset position of objects
            	runRace.setIsRunning(false);
            	mainFrame.reset();
            	mainFrame.getTrackPanel().repaint();
            	mainFrame.getActionPanel().getWinnerNameLabel().setText("    Race Game    ");
            	musicPlayer.stop(audioStream);
            }
        });

          
    	winnerNameLabel = new JLabel("      Race Game    ");
    	winnerNameLabel.setFont(winnerNameLabel.getFont().deriveFont(20.0F));
    	//winnerNameLabel.setLayout();
    	
    	
    	raceStartButton = new JButton("Start Race");
    	//raceStartButton.setLayout(new FlowLayout());
    	raceStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	//start race logic
            	
            	if(runRace == null || !thread.isAlive()){
	            	runRace = new RunRace(mainFrame, mainFrame.getTrackPanel());
	            	thread = new Thread(runRace);
	            	thread.start();
	            	addMusic();
            	}
            }
        });
    	
    	GridBagConstraints constraint = new GridBagConstraints();
    	
    	constraint.gridx = 0;
    	constraint.gridy = 0;
    	constraint.fill = GridBagConstraints.WEST;
    	this.add(callButton, constraint);
    	
    	constraint = new GridBagConstraints();
    	constraint.gridx = 0;
    	constraint.gridy = 1;
    	constraint.fill = GridBagConstraints.CENTER;
    	this.add(winnerNameLabel, constraint);
    	
    	constraint = new GridBagConstraints();
    	constraint.gridx = 0;
    	constraint.gridy = 2;
    	constraint.fill = GridBagConstraints.EAST;
    	this.add(raceStartButton, constraint);
    }
 
    public JLabel getWinnerNameLabel(){
    	musicPlayer.stop(audioStream);
    	return this.winnerNameLabel;
    }
    
   
    public  void addMusic(){
    	
    	try {
			backgroundMusic = new AudioStream(new FileInputStream("ppanther.wav"));
			audioData  = backgroundMusic.getData();
			audioStream = new ContinuousAudioDataStream(audioData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	musicPlayer.start(audioStream);
    	
    }
    

}