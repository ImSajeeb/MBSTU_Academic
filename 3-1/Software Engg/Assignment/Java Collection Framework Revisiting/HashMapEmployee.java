package com.sajeeb.JavaCollectionFramework;
import java.util.*;

public class HashMapEmployee {

        public static void main(String[] args) {
            HashMap<Integer, String> empMap = new HashMap<>();

            empMap.put(1, "HR");
            empMap.put(2, "IT");
            empMap.put(3, "QA");

            System.out.println(empMap);
        }
}
