package com.example.demo.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.demo.security.Permissions.*;

public enum Roles {
    ADMIN(Set.of(STUDENT_READ,
            STUDENT_WRITE,
            COURSE_READ,
            COURSE_WRITE)),
    ADMIN_TRAINEE(Set.of(STUDENT_READ, COURSE_READ)),
    STUDENT(Set.of(STUDENT_READ));

    public Set<Permissions> getPermissions() {
        return permissions;
    }

    private final Set<Permissions> permissions;

    Roles(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
            authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
