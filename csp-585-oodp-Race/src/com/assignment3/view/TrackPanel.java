package com.assignment3.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class TrackPanel extends JPanel{

	private static final long serialVersionUID = 2411307758656790874L;
	
	private DashboardFrame mainFrame;
	
	public TrackPanel(DashboardFrame mainFrame){
		this.mainFrame = mainFrame;
        this.setLayout(new FlowLayout());
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
			try {
				drawBackground(g);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
        mainFrame.drawFigures(g);
    }
 
    private void drawBackground(Graphics g) throws IOException {
    	
    	
    	 Image img = new ImageIcon("funny4.jpg").getImage();
         Dimension size = new Dimension(800, 800);
         setPreferredSize(size);
         setMinimumSize(size);
         setMaximumSize(size);
         setSize(size);
         setLayout(null);
         g.drawImage(img, 0, 0, 800, 715, this);
         
         
    }
    
}