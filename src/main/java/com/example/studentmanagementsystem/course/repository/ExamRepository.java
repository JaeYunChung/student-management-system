package com.example.studentmanagementsystem.course.repository;

import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.course.entity.ExamEntity;
import com.example.studentmanagementsystem.member.domain.StudentEntity;

import java.util.List;

public interface ExamRepository {
    ExamEntity save(ExamEntity exam);
    List<ExamEntity> findByCourseAndStudent(CourseEntity course, StudentEntity student);
}
