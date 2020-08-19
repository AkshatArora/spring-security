package com.example.demo.security;

import static com.example.demo.security.Permissions.COURSE_READ;
import static com.example.demo.security.Permissions.COURSE_WRITE;
import static com.example.demo.security.Permissions.STUDENT_READ;
import static com.example.demo.security.Permissions.STUDENT_WRITE;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Roles {
  ADMIN(Set.of(STUDENT_READ,
    STUDENT_WRITE,
    COURSE_READ,
    COURSE_WRITE)),
  ADMIN_TRAINEE(Set.of(STUDENT_READ, COURSE_READ)),
  STUDENT(Set.of(STUDENT_READ));

  private final Set<Permissions> permissions;

  public Set<Permissions> getPermissions() {
    return permissions;
  }

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
