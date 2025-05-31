package com.example.studentmanagementsystem.grade;

import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaGradeRepository extends JpaRepository<GradeEntity, Long> {
    List<GradeEntity> findAllByStudent(StudentEntity student);
    List<GradeEntity> findAllByCourse(CourseEntity course);
    GradeEntity findByCourseAndStudent(CourseEntity course, StudentEntity student);
}
