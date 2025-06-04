package com.example.studentmanagementsystem.course.service;

import com.example.studentmanagementsystem.course.entity.AssignmentEntity;
import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.course.entity.EvaluationEntity;
import com.example.studentmanagementsystem.course.entity.SubmissionEntity;
import com.example.studentmanagementsystem.course.repository.SubmissionRepository;
import com.example.studentmanagementsystem.event.EventCreateEntity;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final ApplicationEventPublisher eventPublisher;


    @Transactional
    public SubmissionEntity sendSubmission(String contentUrl, CourseEntity course, AssignmentEntity assignment, StudentEntity student, double score, int ratio)
    {
        SubmissionEntity submission = SubmissionEntity.builder()
                .contentUrl(contentUrl)
                .course(course)
                .assignment(assignment)
                .student(student)
                .score(score)
                .ratio(ratio)
                .build();
        SubmissionEntity submissionEntity = submissionRepository.save(submission);
        if (submissionEntity.getId() == null)
            log.info("entity is null");
        eventPublisher.publishEvent(new EventCreateEntity(submissionEntity));
        return submissionEntity;
    }
}
