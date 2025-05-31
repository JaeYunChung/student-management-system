package com.example.studentmanagementsystem.course.repository;

import com.example.studentmanagementsystem.course.entity.AssignmentEntity;

public interface AssignmentRepository {
    AssignmentEntity save(AssignmentEntity assignment);
    void deleteAll();
}
