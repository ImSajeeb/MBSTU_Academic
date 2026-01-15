package org.sajeeb.OOP.Interface;

interface Payment {
    void pay();
}

class Bkash implements Payment {
    public void pay() {
        System.out.println("Payment via Bkash");
    }
}

public class Main {
    public static void main(String[] args) {
        Payment p = new Bkash();
        p.pay();
    }
}
