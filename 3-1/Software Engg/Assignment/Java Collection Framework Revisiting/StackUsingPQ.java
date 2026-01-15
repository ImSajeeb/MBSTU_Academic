package com.sajeeb.JavaCollectionFramework;

import java.util.*;

public class StackUsingPQ {

    static int index = 0;

    static class Element {
        int data;
        int position;

        Element(int data) {
            this.data = data;
            this.position = index++;
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Element> stack =
                new PriorityQueue<>((a, b) -> b.position - a.position);

        stack.add(new Element(10));
        stack.add(new Element(20));
        stack.add(new Element(30));

        while (!stack.isEmpty()) {
            System.out.println(stack.poll().data);
        }
    }
}
