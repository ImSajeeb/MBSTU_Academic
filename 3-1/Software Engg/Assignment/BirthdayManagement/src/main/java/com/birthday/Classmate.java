package com.birthday;

import java.time.LocalDate;

public class Classmate {

    private int id;
    private String name;
    private LocalDate birthdate;

    public Classmate(int id, String name, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }
}
