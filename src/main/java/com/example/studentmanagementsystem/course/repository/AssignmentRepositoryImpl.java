package com.example.studentmanagementsystem.course.repository;

import com.example.studentmanagementsystem.course.entity.AssignmentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AssignmentRepositoryImpl implements AssignmentRepository{

    private final JpaAssignmentRepository jpaAssignmentRepository;

    @Override
    public AssignmentEntity save(AssignmentEntity assignment) {
        return jpaAssignmentRepository.save(assignment);
    }

    @Override
    public void deleteAll() {
        jpaAssignmentRepository.deleteAll();
    }
}
