package com.example.demo.course;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses/")
public class CourseController {

    private final List<Course> COURSES = List.of(
            new Course("History", 1),
            new Course("Biology", 2),
            new Course("Geography", 3),
            new Course("Computer Science", 4),
            new Course("Electronics", 5)
    );

    @GetMapping
    @PreAuthorize("hasAuthority('course:read')")
    public List<Course> getCOURSES() {
        return COURSES;
    }

    @GetMapping(path = "{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ADMIN_TRAINEE')")
    public Course getCourseById(@PathVariable("id") Integer id) {
        return COURSES.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping(path = "{id}")
    @PreAuthorize("hasAuthority('course:write')")
    public String writeCourse(@PathVariable("id") Integer id, @RequestBody Course course) {
        return String.format("course %s is added", course.getName());
    }

    @DeleteMapping(path = "{courseName}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCourse(@PathVariable("courseName") String name) {
        return String.format("Course %s is deleted", name);
    }

    @PutMapping(path = "{id}")
    @PreAuthorize("hasAuthority('course:write')")
    public String updateCourse(@PathVariable("id") Integer id, @RequestBody Course course) {
        return String.format("CourseId %d is updated to : %s", id, course.getName());
    }

}
