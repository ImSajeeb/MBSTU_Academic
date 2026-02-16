package com.example.TestSpringJPA;

import jakarta.persistence.*;

@Entity
@Table(name = "students") // Maps this class to a 'students' table in MySQL
public class Student {

    @Id
    // Since you want to use a specific Student ID, you might want to
    // remove @GeneratedValue if you plan to input the ID manually.
    // If you want the DB to auto-assign it, keep the next line.
    private Long id;

    private String name;

    private Double cgpa; // Changed from email to cgpa (using Double for decimals)

    // Default Constructor (Required by JPA)
    public Student() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }
}