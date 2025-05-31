package com.example.studentmanagementsystem.course.repository;

import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.course.entity.SubmissionEntity;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SubmissionRepositoryImpl implements SubmissionRepository{
    private final JpaSubmissionRepository jpaSubmissionRepository;

    @Override
    public SubmissionEntity save(SubmissionEntity submissionEntity) {
        return jpaSubmissionRepository.save(submissionEntity);
    }

    @Override
    public List<SubmissionEntity> findByCourseAndStudent(CourseEntity course, StudentEntity student) {
        return jpaSubmissionRepository.findByStudentAndCourse(student, course);
    }
}
