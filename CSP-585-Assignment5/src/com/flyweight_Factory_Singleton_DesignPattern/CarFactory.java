package com.flyweight_Factory_Singleton_DesignPattern;

public class CarFactory extends AbstractFactory implements Cloneable{
	
	FordCar ford =null;
	GMCCar gmc = null;
	ToyotaCar toyota = null;
	
 public Car getCar(String car){
	 if(car == null){
         return null;
      }		
      
      if(car.equalsIgnoreCase("Ford") && ford==null){
         return new FordCar();
         
      }else if(car.equalsIgnoreCase("GMC") && gmc == null){
         return new GMCCar();
         
      }else if(car.equalsIgnoreCase("Toyota") && toyota ==null){
         return new ToyotaCar();
      }
      
      return null;
 }
 
 @Override
 public Truck getTruck(String truck){
	 return null;
 }
 
 public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
