package com.sajeeb.JavaCollectionFramework;

import java.util.*;

public class QueueUsingPQ {

    static int count = 0;

    static class Node {
        int data;
        int order;

        Node(int data) {
            this.data = data;
            this.order = count++;
        }
    }

    public static void main(String[] args) {

        PriorityQueue<Node> queue =
                new PriorityQueue<>((x, y) -> x.order - y.order);

        queue.add(new Node(10));
        queue.add(new Node(20));
        queue.add(new Node(30));

        while (!queue.isEmpty()) {
            System.out.println(queue.poll().data);
        }
    }
}
