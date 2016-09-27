package com.flyweight_Factory_Singleton_DesignPattern;

public abstract class AbstractFactory {
 abstract public Car getCar(String car);
 abstract public Truck getTruck(String truck);
}
