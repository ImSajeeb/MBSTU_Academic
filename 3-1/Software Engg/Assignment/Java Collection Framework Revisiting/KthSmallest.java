package com.sajeeb.JavaCollectionFramework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class KthSmallest {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(
                Arrays.asList(7, 4, 9, 1, 3));

        int k = 3;

        Collections.sort(list);
        System.out.println("Kth Smallest Element: " + list.get(k - 1));
    }
}
