package org.sajeeb.OOP.AbstractClass;

abstract class Shape {
    abstract void area();

    void display() {
        System.out.println("Calculating area");
    }
}

class Square extends Shape {
    void area() {
        System.out.println("Area = side × side");
    }
}

public class Main {
    public static void main(String[] args) {
        Shape s = new Square();
        s.display();
        s.area();
    }
}
