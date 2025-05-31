package com.example.studentmanagementsystem.member.repository;

import com.example.studentmanagementsystem.member.domain.Department;
import com.example.studentmanagementsystem.member.domain.DepartmentEnglishExamMapping;
import com.example.studentmanagementsystem.member.domain.StudentEntity;

import java.util.List;

public interface DepartmentEnglishExamMappingRepository {
    List<DepartmentEnglishExamMapping> findAllByDepartment(Department department);
}
