package com.example.studentmanagementsystem.security.repository;

import com.example.studentmanagementsystem.security.Role;

public interface RoleRepository {
    Role findByRole(String role);
}
