package com.example.demo.course;

public class Course {
    public String getName() {
        return name;
    }
    private final String name;

    public Integer getId() {
        return id;
    }

    private final Integer id;

    public Course(String name, Integer id) {
        this.name = name;
        this.id = id;
    }
}
