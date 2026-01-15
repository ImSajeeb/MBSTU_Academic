package com.sajeeb.JavaCollectionFramework;

import java.util.*;

class Student {
    String name;
    int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return name + " (" + age + ")";
    }
}

public class StudentTreeMap {
    public static void main(String[] args) {
        TreeMap<Integer, Student> students = new TreeMap<>();

        students.put(101, new Student("Sajeeb", 24));
        students.put(102, new Student("Khan", 37));

        System.out.println(students);
    }
}

