package com.sajeeb.PracticingMultithreading;

import java.util.*;
public class ParkingPool {

    private final Queue<RegistrarParking> pool = new LinkedList<>();
    private final int capacity ;

    public ParkingPool(int capacity){
        this.capacity = capacity;
    }

    public synchronized void parkCar (RegistrarParking car) throws InterruptedException{
        while (pool.size()==capacity){
            wait();
        }
        pool.add(car);
        System.out.println("Car "+car.getCarID() + "parked");
        notifyAll();
    }

    public synchronized RegistrarParking getCar() throws InterruptedException{
        while (pool.isEmpty()){
            wait();
        }
        RegistrarParking car = pool.poll();
        notifyAll();
        return car ;

    }
}
