package com.example.studentmanagementsystem.security.repository;

import com.example.studentmanagementsystem.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
