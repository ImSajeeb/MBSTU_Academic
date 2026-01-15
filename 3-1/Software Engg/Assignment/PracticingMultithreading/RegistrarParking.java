package com.sajeeb.PracticingMultithreading;

public class RegistrarParking {

    private static int count = 1 ;
    private final int carID;

    public RegistrarParking(){
        this.carID = count++;
    }
    public int getCarID(){
        return carID;
    }
}
