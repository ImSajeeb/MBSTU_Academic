package org.sajeeb.OOP.ClassesandObjects;


class Student {
    String name;
    int marks;

    void showResult() {
        System.out.println(name + " scored " + marks);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name = "Sajeeb";
        s1.marks = 85;
        s1.showResult();
    }
}

