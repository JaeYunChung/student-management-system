package com.example.studentmanagementsystem.member.repository;

import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.member.Student;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import com.example.studentmanagementsystem.security.Member;

import java.util.List;

public interface StudentRepository {
    StudentEntity save(Student student);
    List<StudentEntity> saveAll(List<StudentEntity> studentEntities);
    List<StudentEntity> findAll();
    List<StudentEntity> findAllByCourse(CourseEntity course);
    StudentEntity findById(Long id);
    StudentEntity findByMember(Member member);
    void deleteAll();
    void deleteById(Long id);
}
