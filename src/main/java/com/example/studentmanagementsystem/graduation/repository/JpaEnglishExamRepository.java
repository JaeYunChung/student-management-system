package com.example.studentmanagementsystem.graduation.repository;

import com.example.studentmanagementsystem.graduation.EnglishExamEntity;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaEnglishExamRepository extends JpaRepository<EnglishExamEntity, Long> {
    List<EnglishExamEntity> findAllByStudent(StudentEntity student);
}
