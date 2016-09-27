package com.flyweight_Factory_Singleton_DesignPattern;

import java.io.IOException;


public class VehicleFlyWeight implements Cloneable {

	private VehicleFlyWeight() {

	}

	Car objCar;
	Truck objTruck;
	static VehicleFlyWeight objVehicleFlyWeight;
	
	// factory Objects
	AbstractFactory carFactory = VehicleFactoryProducer.getFactory("Car");
	AbstractFactory truckFactory = VehicleFactoryProducer.getFactory("Truck");

	// Method to get different car .
	public void getCar(String car) {
		objCar = carFactory.getCar(car);
		objCar.getCar();
	}

	// Method to get different trucks .
	public void getTruck(String truck) {
		objTruck = truckFactory.getTruck(truck);
		objTruck.getTruck();
	}

	public static void main(String args[]) throws IOException {
		//creating Singleton object of VehicleFlyWeight class.
		getVehicleFlyWeightInstance();

		// FlyWeight Design pattern implementation.
		String brand[] = { "Toyota", "GMC", "Ford" };

		System.out.println("Trucks :::::::::::::::::::::::::::::::");
		for (String strBrand : brand) {
			objVehicleFlyWeight.getTruck(strBrand);
		}

		System.out.println("");
		System.out.println("Car ::::::::::::::::::::::::::::::::::");
		for (String strBrand : brand) {
			objVehicleFlyWeight.getCar(strBrand);
		}
	}
	
	public static synchronized VehicleFlyWeight getVehicleFlyWeightInstance() throws IOException {
		if (objVehicleFlyWeight == null) {
			objVehicleFlyWeight = new VehicleFlyWeight();
		}
		return objVehicleFlyWeight;
	}

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

}
