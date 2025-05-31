package com.example.studentmanagementsystem.member.repository;

import com.example.studentmanagementsystem.member.domain.Department;
import com.example.studentmanagementsystem.member.domain.DepartmentEnglishExamMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DepartmentEnglishExamMappingRepositoryImpl implements DepartmentEnglishExamMappingRepository{

    private final JpaDepartmentEnglishExamMappingRepository jpaDepartmentEnglishExamMappingRepository;

    @Override
    public List<DepartmentEnglishExamMapping> findAllByDepartment(Department department) {
        return jpaDepartmentEnglishExamMappingRepository.findAllByDepartment(department);
    }
}
