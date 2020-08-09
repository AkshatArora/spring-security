package com.example.demo.security;

public enum Permissions {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    public String getPermission() {
        return permission;
    }

    private final String permission;
    Permissions(String permission) {
        this.permission = permission;
    }
}
