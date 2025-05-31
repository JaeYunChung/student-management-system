package com.example.studentmanagementsystem.member.service;

import com.example.studentmanagementsystem.course.dto.CreateCourseDto;
import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.course.repository.CourseRepository;
import com.example.studentmanagementsystem.course.repository.StudentCourseMappingRepositoryImpl;
import com.example.studentmanagementsystem.course.service.CourseService;
import com.example.studentmanagementsystem.member.Degree;
import com.example.studentmanagementsystem.member.domain.Department;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import com.example.studentmanagementsystem.member.dto.CreateStudentDto;
import com.example.studentmanagementsystem.member.repository.DepartmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    private StudentCourseMappingRepositoryImpl studentCourseMappingRepositoryImpl;


    @BeforeEach
    void setup()
    {
        CreateCourseDto dto1 = new CreateCourseDto("자료구조", "정재윤", 50, 3, "SPRING", "CyberSecurity");
        CreateCourseDto dto2 = new CreateCourseDto("데이터베이스", "정재윤", 40, 3,  "SPRING", "CyberSecurity");
        courseService.createCourse(dto1);
        courseService.createCourse(dto2);
    }

    @AfterEach
    void initialize(){
        courseRepository.deleteAll();
    }

    @Test
    void testGetRequiredOrOptionCourses(){
        final int enterYear = 2025;
        final String departmentName = "CyberSecurity";
        Department department = departmentRepository.findByName(departmentName).get();
        List<String> requiredCourse = new ArrayList<>(departmentService.getRequiredCourses(department, enterYear));
        List<String> optionCourse = new ArrayList<>(departmentService.getOptionRequiredCourses(department, enterYear));
        assertEquals("자료구조", requiredCourse.get(0));
        assertEquals("데이터베이스", optionCourse.get(0));
    }
}