package org.sajeeb.OOP.InheritanceProtectedAccess;

class Vehicle {
    protected String fuel = "Petrol";
}

class Bike extends Vehicle {
    void showFuel() {
        System.out.println("Bike uses " + fuel);
    }
}

public class Main {
    public static void main(String[] args) {
        Bike b = new Bike();
        b.showFuel();
    }
}
