package com.example.demo.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private static final List<Student> STUDENTS = Arrays.asList(
      new Student(1, "James Bond"),
      new Student(2, "Maria Jones"),
      new Student(3, "Anna Smith")
    );

    @GetMapping(path = "{studentId}")
    @PreAuthorize("permitAll()")
    public Student getStudent(@PathVariable("studentId") Integer studentId) {
        return STUDENTS.stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "Student " + studentId + " does not exists"
                ));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public List<Student> postStudent() {
        return STUDENTS;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")  //ADMIN_ROLE has four permissions - STUDENT_READ, STUDENT_WRITE
    //COURSE_READ, AND COURSE_WRITE
    @DeleteMapping(path = "{studentId}")
    public String deleteStudent(@PathVariable("studentId") Integer id) {
        return "Student id is deleted";
    }
}
