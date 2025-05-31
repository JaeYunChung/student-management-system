package com.example.studentmanagementsystem.course.repository;

import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.course.entity.SubmissionEntity;
import com.example.studentmanagementsystem.member.domain.StudentEntity;

import java.util.List;

public interface SubmissionRepository {
    SubmissionEntity save(SubmissionEntity submissionEntity);
    List<SubmissionEntity> findByCourseAndStudent(CourseEntity course, StudentEntity student);
}
