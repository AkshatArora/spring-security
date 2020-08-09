package com.example.demo.course;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses/")
public class CourseController {

    private final List<Course> COURSES = List.of(
            new Course("History"),
            new Course("Biology"),
            new Course("Geography"),
            new Course("Computer Science"),
            new Course("Electronics")
    );

    @GetMapping("hasAuthority('course:read')")
    public List<Course> getCOURSES() {
        return COURSES;
    }

    @PostMapping(path = "{coursename}")
    @PreAuthorize("hasAuthority('course:write')")
    public String writeCourse(@PathVariable("coursename") String name) {
        COURSES.add(new Course(name));
        return "Course is added.";
    }
}
