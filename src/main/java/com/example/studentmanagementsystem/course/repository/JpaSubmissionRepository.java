package com.example.studentmanagementsystem.course.repository;

import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.course.entity.SubmissionEntity;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaSubmissionRepository extends JpaRepository<SubmissionEntity, Long> {
    List<SubmissionEntity> findByStudentAndCourse(StudentEntity student, CourseEntity course);
}
