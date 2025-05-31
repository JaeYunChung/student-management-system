package com.example.studentmanagementsystem.member.repository;

import com.example.studentmanagementsystem.member.domain.ProfessorEntity;

import java.util.Optional;

public interface ProfessorRepository {
    Optional<ProfessorEntity> findByName(String name);
}
