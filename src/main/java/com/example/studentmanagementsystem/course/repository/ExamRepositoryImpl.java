package com.example.studentmanagementsystem.course.repository;

import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.course.entity.ExamEntity;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExamRepositoryImpl implements ExamRepository{
    private final JpaExamRepository jpaExamRepository;

    @Override
    public ExamEntity save(ExamEntity exam) {
        return jpaExamRepository.save(exam);
    }

    @Override
    public List<ExamEntity> findByCourseAndStudent(CourseEntity course, StudentEntity student) {
        return jpaExamRepository.findByCourseAndStudent(course, student);
    }
}
