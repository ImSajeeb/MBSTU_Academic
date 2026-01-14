package com.sajeeb;

import java.util.*;

public class DSAAssignment {

        public static void main(String[] args) {


// LinkedList
            LinkedList<Integer> linkedList = new LinkedList<>();
            linkedList.add(10);
            linkedList.addFirst(5);
            linkedList.addLast(20);
            System.out.println("LinkedList: " + linkedList);


// PriorityQueue
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            pq.add(30);
            pq.add(10);
            pq.add(20);
            System.out.print("PriorityQueue: ");
            while (!pq.isEmpty()) {
                System.out.print(pq.poll() + " ");
            }
            System.out.println();


// Deque (ArrayDeque)
            Deque<Integer> deque = new ArrayDeque<>();
            deque.addFirst(1);
            deque.addLast(2);
            deque.addLast(3);
            System.out.println("Deque: " + deque);


// HashMap
            HashMap<Integer, String> hashMap = new HashMap<>();
            hashMap.put(3, "C");
            hashMap.put(1, "A");
            hashMap.put(2, "B");
            System.out.println("HashMap: " + hashMap);


// TreeMap
            TreeMap<Integer, String> treeMap = new TreeMap<>();
            treeMap.put(3, "C");
            treeMap.put(1, "A");
            treeMap.put(2, "B");
            System.out.println("TreeMap: " + treeMap);


// HashSet
            HashSet<Integer> hashSet = new HashSet<>();
            hashSet.add(10);
            hashSet.add(10);
            hashSet.add(20);
            System.out.println("HashSet: " + hashSet);
        }
}
