package com.example.studentmanagementsystem.member.repository;

import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.member.Student;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import com.example.studentmanagementsystem.security.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository{
    private final JpaStudentRepository jpaStudentRepository;

    @Override
    public StudentEntity save(Student student) {
        StudentEntity studentEntity = student.toEntity();
        return jpaStudentRepository.save(studentEntity);
    }

    @Override
    public List<StudentEntity> saveAll(List<StudentEntity> studentEntities) {
        return jpaStudentRepository.saveAll(studentEntities);
    }

    @Override
    public List<StudentEntity> findAll() {
        return jpaStudentRepository.findAll();
    }

    @Override
    public List<StudentEntity> findAllByCourse(CourseEntity course) {
        return jpaStudentRepository.findAllByCourse(course);
    }

    @Override
    public StudentEntity findById(Long id) {
        return jpaStudentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 학생이 존재하지 않습니다."));
    }

    @Override
    public StudentEntity findByMember(Member member) {
        return jpaStudentRepository.findByMember(member);
    }

    @Override
    public void deleteAll() {
        jpaStudentRepository.deleteAll();
    }

    @Override
    public void deleteById(Long id) {
        jpaStudentRepository.deleteById(id);
    }
}
