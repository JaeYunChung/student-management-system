package com.example.studentmanagementsystem.member.service;

import com.example.studentmanagementsystem.member.TypeOfCourse;
import com.example.studentmanagementsystem.member.domain.Department;
import com.example.studentmanagementsystem.member.domain.DepartmentCourseMapping;
import com.example.studentmanagementsystem.member.repository.DepartmentCourseMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentCourseMappingRepository departmentCourseMappingRepository;

    @Transactional
    public Set<String> getRequiredCourses(Department department, int enterYear) {
        return departmentCourseMappingRepository.findAllByDepartmentAndEnterYear(department, enterYear)
                .stream()
                .filter(each -> each.getType().equals(TypeOfCourse.REQUIRED))
                .map(DepartmentCourseMapping::getCourseName)
                .collect(Collectors.toSet());
    }
    @Transactional
    public Set<String> getOptionRequiredCourses(Department department, int enterYear){
        return departmentCourseMappingRepository.findAllByDepartmentAndEnterYear(department, enterYear)
                .stream()
                .filter(each -> each.getType().equals(TypeOfCourse.OPTION))
                .map(DepartmentCourseMapping::getCourseName)
                .collect(Collectors.toSet());
    }
}
