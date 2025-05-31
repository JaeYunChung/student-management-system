package com.example.studentmanagementsystem.graduation.service;

import com.example.studentmanagementsystem.graduation.EnglishExam;
import com.example.studentmanagementsystem.graduation.EnglishExamEntity;
import com.example.studentmanagementsystem.graduation.repository.EnglishExamRepository;
import com.example.studentmanagementsystem.member.Student;
import com.example.studentmanagementsystem.member.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EnglishExamService {
    private final EnglishExamRepository englishExamRepository;
    private final StudentRepository studentRepository;
    public EnglishExamEntity submitEnglishExamScore(Student student, EnglishExam type, int score){
        EnglishExamEntity entity = EnglishExamEntity.builder()
                .type(type)
                .student(student.toEntity())
                .score(score)
                .build();
        return englishExamRepository.save(entity);
    }
}
