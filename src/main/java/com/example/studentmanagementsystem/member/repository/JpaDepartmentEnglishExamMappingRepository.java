package com.example.studentmanagementsystem.member.repository;

import com.example.studentmanagementsystem.member.domain.Department;
import com.example.studentmanagementsystem.member.domain.DepartmentEnglishExamMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaDepartmentEnglishExamMappingRepository extends JpaRepository<DepartmentEnglishExamMapping, Long> {
    List<DepartmentEnglishExamMapping> findAllByDepartment(Department department);
}
