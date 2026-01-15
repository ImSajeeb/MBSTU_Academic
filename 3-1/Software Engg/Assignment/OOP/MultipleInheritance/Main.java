package org.sajeeb.OOP.MultipleInheritance;

interface Camera {
    void click();
}

interface Music {
    void play();
}

class Smartphone implements Camera, Music {
    public void click() {
        System.out.println("Photo clicked");
    }

    public void play() {
        System.out.println("Music playing");
    }
}

public class Main {
    public static void main(String[] args) {
        Smartphone s = new Smartphone();
        s.click();
        s.play();
    }
}
