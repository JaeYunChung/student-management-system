package com.example.studentmanagementsystem.graduation.repository;

import com.example.studentmanagementsystem.graduation.EnglishExamEntity;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EnglishExamRepositoryImpl implements EnglishExamRepository
{
    private final JpaEnglishExamRepository jpaEnglishExamRepository;

    @Override
    public List<EnglishExamEntity> findAllByStudent(StudentEntity student) {
        return jpaEnglishExamRepository.findAllByStudent(student);
    }

    @Override
    public EnglishExamEntity save(EnglishExamEntity entity) {
        return jpaEnglishExamRepository.save(entity);
    }

    @Override
    public void deleteAll() {
        jpaEnglishExamRepository.deleteAll();
    }
}
