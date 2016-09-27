package com.assignment1.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.assignment1.model.Homer;
import com.assignment1.model.Ellipse;
import com.assignment1.model.Goofy;
import com.assignment1.model.Mickey;
import com.assignment1.model.Minny;
import com.assignment1.model.Shape;
import com.assignment1.model.SpongeBob;
import com.assignment1.view.DashboardFrame;

public class RaceMain {

	private List<Shape> shapesList = new ArrayList<>();
	
	public List<Shape> getShapesList() {
		return shapesList;
	}

	public static void main(String[] args) throws IOException {
		
		RaceMain raceMain = new RaceMain();
		
		raceMain.addRacingShape(new Ellipse("Ellipse"));
		raceMain.addRacingShape(new Minny("Minny"));
		raceMain.addRacingShape(new SpongeBob("SpongeBob"));
		raceMain.addRacingShape(new Mickey("Mickey"));
		raceMain.addRacingShape(new Goofy("Goofy"));
		raceMain.addRacingShape(new Homer("Homer"));
		
		DashboardFrame obj = new DashboardFrame(raceMain);
	}
	
	public void addRacingShape(Shape obj){
		shapesList.add(obj);
	}
}