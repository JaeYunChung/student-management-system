package com.example.studentmanagementsystem.member.repository;

import com.example.studentmanagementsystem.member.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaDepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);
    Department save(Department department);
}
