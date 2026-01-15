package com.sajeeb.PracticingMultithreading;


import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) throws Exception {

        ParkingPool pool = new ParkingPool(5);

        // Create parking agents
        new ParkingAgent("Agent-1", pool).start();
        new ParkingAgent("Agent-2", pool).start();
        new ParkingAgent("Agent-3", pool).start();

        Scanner scanner = new Scanner(System.in);
        int carCount = 0;

        while (carCount < 10) {
            System.out.println("Press ENTER to register a car for parking...");
            scanner.nextLine();

            RegistrarParking car = new RegistrarParking();
            pool.parkCar(car);
            carCount++;
        }

        scanner.close();
    }
}
