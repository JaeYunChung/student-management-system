package com.example.studentmanagementsystem.course.service;

import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.course.entity.ExamEntity;
import com.example.studentmanagementsystem.course.repository.ExamRepository;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public ExamEntity createExamEntity(CourseEntity course, StudentEntity student, double score, int ratio){
        ExamEntity exam = ExamEntity.builder()
                .course(course)
                .student(student)
                .score(score)
                .ratio(ratio)
                .build();

        ExamEntity entity =  examRepository.save(exam);
        if (entity.getId() == null) {
            log.info("enitty가 null입니다.");
        }
        eventPublisher.publishEvent(entity);
        return entity;
    }
}
