package com.sajeeb;

import java.util.Vector;

public class VectorCapacityTest {

    public static void main(String[] args) throws Exception {

        Vector<Integer> vector = new Vector<>();


        int previousCapacity = 0;

        for (int i = 1; i <= 20; i++) {
            vector.add(i);
            int currentCapacity = vector.capacity();
            if (currentCapacity != previousCapacity) {
                System.out.println(
                        "Size: " + vector.size() +
                                " | Capacity increased to: " + currentCapacity
                );
                previousCapacity = currentCapacity;
            }
        }
    }
}
