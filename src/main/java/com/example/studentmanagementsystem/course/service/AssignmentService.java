package com.example.studentmanagementsystem.course.service;

import com.example.studentmanagementsystem.course.entity.AssignmentEntity;
import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.course.repository.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public AssignmentEntity createAssignmentEntity(CourseEntity course, String contentUrl){
        AssignmentEntity assignment = AssignmentEntity.builder()
                .course(course)
                .contentUrl(contentUrl)
                .createdAt(Instant.now())
                .limitAt(Instant.now().plus(5, ChronoUnit.DAYS))
                .build();
        return assignmentRepository.save(assignment);
    }
}
