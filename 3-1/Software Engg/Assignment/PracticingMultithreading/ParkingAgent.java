package com.sajeeb.PracticingMultithreading;

public class ParkingAgent extends Thread {

    private final ParkingPool parkingPool;
    private final String agentName;

    public ParkingAgent(String name, ParkingPool pool) {
        this.agentName = name;
        this.parkingPool = pool;
    }

    @Override
    public void run() {
        while (true) {
            try {
                RegistrarParking car = parkingPool.getCar();
                System.out.println(agentName + " parked Car " + car.getCarID());
                Thread.sleep(1000); // simulate parking time
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
