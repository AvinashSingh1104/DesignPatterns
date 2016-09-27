package com.assignment4;

public class ShapeFactory extends AbstractFactory {
	
	   @Override
	   public Shape getShape(String shapeType){
	   
	      if(shapeType == null){
	         return null;
	      }		
	      
		if (shapeType.equalsIgnoreCase("Ellipse")) {
			return new Ellipse(shapeType);

		} else if (shapeType.equalsIgnoreCase("Goofy")) {
			return new Goofy(shapeType);

		} else if (shapeType.equalsIgnoreCase("SpongeBob")) {
			return new SpongeBob(shapeType);

		} else if (shapeType.equalsIgnoreCase("Minny")) {
			return new Minny(shapeType);

		} else if (shapeType.equalsIgnoreCase("Mickey")) {
			return new Mickey(shapeType);

		} else if (shapeType.equalsIgnoreCase("Homer")) {
			return new Homer(shapeType);

		}

		return null;
	   }
	   
	   @Override
	   Name getName(String color) {
	      return null;
	   }
	}