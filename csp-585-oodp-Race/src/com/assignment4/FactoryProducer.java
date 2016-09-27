package com.assignment4;

public class FactoryProducer {

	public static synchronized AbstractFactory getFactory(String choice){
		   
	      if(choice.equalsIgnoreCase("Shape")){
	         return new ShapeFactory();
	         
	      }else if(choice.equalsIgnoreCase("Name")){
	         return new NameFactory();
	      }
	      return null;
	   }
}
