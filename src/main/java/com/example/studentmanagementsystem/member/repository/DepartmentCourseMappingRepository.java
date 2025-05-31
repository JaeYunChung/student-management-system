package com.example.studentmanagementsystem.member.repository;

import com.example.studentmanagementsystem.member.domain.Department;
import com.example.studentmanagementsystem.member.domain.DepartmentCourseMapping;

import java.util.List;

public interface DepartmentCourseMappingRepository {
    List<String> findCourseNamesByDepartmentAndEnterYear(Department department, int enterYear);
    List<DepartmentCourseMapping> findAllByDepartmentAndEnterYear(Department department, int enterYear);
}
