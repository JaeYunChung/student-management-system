package com.example.studentmanagementsystem.member.repository;

import com.example.studentmanagementsystem.member.domain.Department;
import com.example.studentmanagementsystem.member.domain.DepartmentCourseMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DepartmentCourseMappingRepositoryImpl implements DepartmentCourseMappingRepository {

    private final JpaDepartmentCourseMappingRepository jpaDepartmentCourseMappingRepository;

    @Override
    public List<String> findCourseNamesByDepartmentAndEnterYear(Department department, int enterYear) {
        return jpaDepartmentCourseMappingRepository.findCourseNamesByDepartmentAndEnterYear(department, enterYear);
    }

    @Override
    public List<DepartmentCourseMapping> findAllByDepartmentAndEnterYear(Department department, int enterYear) {
        return jpaDepartmentCourseMappingRepository.findAllByDepartmentAndEnterYear(department, enterYear);
    }
}
