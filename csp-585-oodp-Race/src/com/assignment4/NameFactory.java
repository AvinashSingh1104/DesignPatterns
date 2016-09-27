package com.assignment4;

public class NameFactory extends AbstractFactory {
	
	   @Override
	   public Shape getShape(String shapeType){
	      return null;
	   }
	   
	   @Override
	   Name getName(String name) {
	   
	      if(name == null){
	         return null;
	      }		
	      
		if (name.equalsIgnoreCase("Ellipse")) {
			return new NameEllipse();

		} else if (name.equalsIgnoreCase("Goofy")) {
			return new NameGoofy();

		} else if (name.equalsIgnoreCase("SpongeBob")) {
			return new NameSpongeBob();

		} else if (name.equalsIgnoreCase("Minny")) {
			return new NameMinny();

		} else if (name.equalsIgnoreCase("Mickey")) {
			return new NameMickey();

		} else if (name.equalsIgnoreCase("Homer")) {
			return new NameHomer();

		}
	      
	      return null;
	   }
	}