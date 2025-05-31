package com.example.studentmanagementsystem.member.repository;

import com.example.studentmanagementsystem.member.domain.ProfessorEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProfessorRepositoryImpl implements ProfessorRepository{
    private final JpaProfessorRepository jpaProfessorRepository;
    @Override
    public Optional<ProfessorEntity> findByName(String name) {
        return jpaProfessorRepository.findByName(name);
    }
}
