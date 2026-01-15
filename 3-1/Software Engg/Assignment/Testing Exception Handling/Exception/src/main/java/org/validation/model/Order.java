package org.validation.model;

public class Order {

    private int age;
    private String department;

    public Order(int age, String department) {
        this.age = age;
        this.department = department;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }
}
