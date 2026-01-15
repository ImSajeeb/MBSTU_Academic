package com.sajeeb.JavaCollectionFramework;

import java.util.*;

public class LinkedListEqual {
    public static void main(String[] args) {
        LinkedList<Integer> list1 = new LinkedList<>(Arrays.asList(1, 2, 3));
        LinkedList<Integer> list2 = new LinkedList<>(Arrays.asList(4, 29, 6));

        System.out.println("Are Equal = " + list1.equals(list2));
    }
}
