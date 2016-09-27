package com.assignment4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RaceMain {

	private RaceMain(){
	}
	
	private static DashboardFrame objDashboardFrame = null;
	private static RaceMain raceMain = null;
	
	
	private List<Shape> shapeList = new ArrayList<>();
	private static List<String> nameList = new ArrayList<>();
	static String[] myStrings = new String[] {"Ellipse","Goofy","SpongeBob","Minny","Mickey","Homer"};

	
	public static void main(String[] args) throws IOException {

		//Creating Singleton object of RaceMain class
		getRaceMainInstance();
		
		// get shape factory
		AbstractFactory shapeFactory = FactoryProducer.getFactory("Shape");
		
		//creating list of raceMain from shapeFactory
		for(String objShape : myStrings) {
			raceMain.addRacingCartoon(shapeFactory.getShape(objShape));
		}
		
		// get name factory
		AbstractFactory colorFactory = FactoryProducer.getFactory("Name");
		
		//creating list of names from colorFactory
		for(String objShape : myStrings) {
			nameList.add(colorFactory.getName(objShape).getName());
		}

		//Creating Singleton object of DashboardFrame class
		getDashboardFrameInstance();

	}
	
	public static synchronized RaceMain getRaceMainInstance() throws IOException {
		if (raceMain == null) {
			raceMain = new RaceMain();
		}
		return raceMain;
	}
	
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	public static synchronized DashboardFrame getDashboardFrameInstance() throws IOException {
		if (objDashboardFrame == null) {
			objDashboardFrame = new DashboardFrame(raceMain, nameList);
		}
		return objDashboardFrame;
	}
	
	public void addRacingCartoon(Shape obj) {
		shapeList.add(obj);
	}

	public List<Shape> getShapesList() {
		return shapeList;
	}
}
