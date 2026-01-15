package com.sajeeb.JavaCollectionFramework;

import java.util.*;
public class WordFrequencyTreeMap {

        public static void main(String[] args) {
            String text = "java is fun java is powerful  and i love it";

            TreeMap<String, Integer> map = new TreeMap<>();

            for (String word : text.split(" ")) {
                if (map.containsKey(word)) {
                    map.put(word, map.get(word) + 1);
                } else {
                    map.put(word, 1);
                }
            }

            System.out.println(map);
        }
    }
