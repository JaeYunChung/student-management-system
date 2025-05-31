package com.example.studentmanagementsystem.member.repository;

import com.example.studentmanagementsystem.member.domain.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DepartmentRepositoryImpl implements DepartmentRepository{

    private final JpaDepartmentRepository jpaDepartmentRepository;

    @Override
    public Optional<Department> findByName(String name) {
        return jpaDepartmentRepository.findByName(name);
    }

    @Override
    public Department save(Department department) {
        return jpaDepartmentRepository.save(department);
    }
}
