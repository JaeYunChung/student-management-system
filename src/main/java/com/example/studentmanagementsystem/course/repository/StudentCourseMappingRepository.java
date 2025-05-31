package com.example.studentmanagementsystem.course.repository;

import com.example.studentmanagementsystem.course.entity.StudentCourseMapping;
import com.example.studentmanagementsystem.member.domain.StudentEntity;

import java.util.List;

public interface StudentCourseMappingRepository {
    void deleteAll();
    StudentCourseMapping save(StudentCourseMapping mapping);
    List<StudentCourseMapping> saveAll(List<StudentCourseMapping> mappings);
    List<StudentCourseMapping> findAll();
    List<StudentCourseMapping> findAllByStudent(StudentEntity student);
}
