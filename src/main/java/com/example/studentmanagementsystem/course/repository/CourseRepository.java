package com.example.studentmanagementsystem.course.repository;

import com.example.studentmanagementsystem.member.TypeOfCourse;
import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.member.domain.Department;

import java.util.List;

public interface CourseRepository {
    CourseEntity save(CourseEntity course);
    CourseEntity findById(Long id);
    List<CourseEntity> findAll();
    void deleteAll();
    void deleteById(Long id);
}
