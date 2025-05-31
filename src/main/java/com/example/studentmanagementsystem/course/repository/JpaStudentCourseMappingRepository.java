package com.example.studentmanagementsystem.course.repository;

import com.example.studentmanagementsystem.course.entity.StudentCourseMapping;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaStudentCourseMappingRepository extends JpaRepository<StudentCourseMapping, Long> {
    List<StudentCourseMapping> findAllByStudent(StudentEntity student);
}
