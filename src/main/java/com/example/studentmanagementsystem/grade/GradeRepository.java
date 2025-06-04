package com.example.studentmanagementsystem.grade;

import com.example.studentmanagementsystem.course.Course;
import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface GradeRepository {
    void save(GradeEntity grade);
    void saveAll(List<GradeEntity> grades);
    List<GradeEntity> findAllByStudent(StudentEntity student);
    List<GradeEntity> findAllByCourse(CourseEntity course);
    Optional<GradeEntity> findByCourseAndStudent(CourseEntity course, StudentEntity student);
    void deleteAll();
    void deleteById(Long id);

    List<GradeEntity> findAll();
}
