package com.sajeeb;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ArrayListCapacityTest {

    public static void main(String[] args) throws Exception {

        ArrayList<Integer> list = new ArrayList<>();

        Field dataField = ArrayList.class.getDeclaredField("elementData");
        dataField.setAccessible(true);

        int previousCapacity = 0;

        for (int i = 1; i <= 20; i++) {
            list.add(i);

            Object[] internalArray = (Object[]) dataField.get(list);
            int currentCapacity = internalArray.length;

            if (currentCapacity != previousCapacity) {
                System.out.println(
                        "Size: " + list.size() +
                                " | Capacity increased to: " + currentCapacity
                );
                previousCapacity = currentCapacity;
            }
        }
    }
}
