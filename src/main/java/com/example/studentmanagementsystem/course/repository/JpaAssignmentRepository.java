package com.example.studentmanagementsystem.course.repository;

import com.example.studentmanagementsystem.course.entity.AssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAssignmentRepository extends JpaRepository<AssignmentEntity, Long> {
}
