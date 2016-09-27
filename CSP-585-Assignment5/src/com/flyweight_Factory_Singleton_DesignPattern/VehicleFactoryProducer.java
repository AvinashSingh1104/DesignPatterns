package com.flyweight_Factory_Singleton_DesignPattern;

public class VehicleFactoryProducer {

	public static AbstractFactory getFactory(String Choice){
		
		if(Choice.equalsIgnoreCase("Car")){
			return new CarFactory();
		}else if(Choice.equalsIgnoreCase("Truck")){
			return new TruckFactory();
		}
		return null;
	}
}
