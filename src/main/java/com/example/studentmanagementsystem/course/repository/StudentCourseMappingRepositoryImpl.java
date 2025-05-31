package com.example.studentmanagementsystem.course.repository;

import com.example.studentmanagementsystem.course.entity.StudentCourseMapping;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentCourseMappingRepositoryImpl implements StudentCourseMappingRepository{

    private final JpaStudentCourseMappingRepository jpaStudentCourseMappingRepository;

    @Override
    public void deleteAll() {
        jpaStudentCourseMappingRepository.deleteAll();
    }

    @Override
    public StudentCourseMapping save(StudentCourseMapping mapping) {
        return jpaStudentCourseMappingRepository.save(mapping);
    }

    @Override
    public List<StudentCourseMapping> saveAll(List<StudentCourseMapping> mappings) {
        return jpaStudentCourseMappingRepository.saveAll(mappings);
    }

    @Override
    public List<StudentCourseMapping> findAll() {
        return jpaStudentCourseMappingRepository.findAll();
    }

    @Override
    public List<StudentCourseMapping> findAllByStudent(StudentEntity student) {
        return jpaStudentCourseMappingRepository.findAllByStudent(student);
    }
}
