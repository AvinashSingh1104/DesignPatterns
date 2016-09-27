package com.flyweight_Factory_Singleton_DesignPattern;

public class TruckFactory extends AbstractFactory implements Cloneable{
	FordTruck ford=null;
	GMCCar gmc = null;
	ToyotaCar toyota = null;
	
	@Override
	 public Truck getTruck(String truck){
		if(truck == null){
	         return null;
	      }		
	      
	      if(truck.equalsIgnoreCase("Ford") && ford==null){
	         return new FordTruck();
	         
	      }else if(truck.equalsIgnoreCase("GMC") && gmc == null){
	         return new GMCTruck();
	         
	      }else if(truck.equalsIgnoreCase("Toyota") && toyota ==null){
	         return new ToyotaTruck();
	      }
	      
	      return null;
	 }
	
	 @Override
	 public Car getCar(String car){
		 return null;
	 }
	 
	 public Object clone() throws CloneNotSupportedException {
			throw new CloneNotSupportedException();
		}
	}
