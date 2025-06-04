package com.example.studentmanagementsystem.course.repository;

import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.course.entity.ExamEntity;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaExamRepository extends JpaRepository<ExamEntity, Long> {
    List<ExamEntity> findByCourseAndStudent(CourseEntity course, StudentEntity student);
}
