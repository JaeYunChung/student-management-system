package com.example.studentmanagementsystem.member.repository;

import com.example.studentmanagementsystem.member.domain.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
    Optional<ProfessorEntity> findByName(String name);
}
