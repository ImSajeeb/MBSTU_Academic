package com.sajeeb;



public class PRNGDemo {

    public static void main(String[] args) {

        System.out.println("Random Int: " + CustomPRNG.randomInt());
        System.out.println("Random Int (0-50): " + CustomPRNG.randomInt(50));

        System.out.println("Random Double: " + CustomPRNG.randomDouble());
        System.out.println("Random Double (5-10): " +
                CustomPRNG.randomDouble(5.0, 10.0));

        System.out.println("Random Float: " + CustomPRNG.randomFloat());

        System.out.println("Random Mixed: " + CustomPRNG.randomMixed());
    }
}
