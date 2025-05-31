package com.example.studentmanagementsystem.graduation.repository;

import com.example.studentmanagementsystem.graduation.EnglishExamEntity;
import com.example.studentmanagementsystem.member.domain.StudentEntity;

import java.util.List;

public interface EnglishExamRepository {
    List<EnglishExamEntity> findAllByStudent(StudentEntity student);
    EnglishExamEntity save(EnglishExamEntity entity);
    void deleteAll();
}
