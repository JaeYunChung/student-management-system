package com.example.studentmanagementsystem.member.repository;

import com.example.studentmanagementsystem.member.domain.Department;

import java.util.Optional;

public interface DepartmentRepository {
    Optional<Department> findByName(String name);
    Department save(Department department);
}
