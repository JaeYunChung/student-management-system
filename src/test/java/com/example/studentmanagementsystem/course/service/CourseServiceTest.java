package com.example.studentmanagementsystem.course.service;

import com.example.studentmanagementsystem.course.dto.CreateCourseDto;
import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.course.entity.StudentCourseMapping;
import com.example.studentmanagementsystem.course.repository.CourseRepository;
import com.example.studentmanagementsystem.course.repository.StudentCourseMappingRepository;
import com.example.studentmanagementsystem.member.Degree;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import com.example.studentmanagementsystem.member.dto.CreateStudentDto;
import com.example.studentmanagementsystem.member.repository.StudentRepository;
import com.example.studentmanagementsystem.member.service.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseServiceTest {

    @Autowired
    CourseService courseService;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentCourseMappingRepository studentCourseMappingRepository;

    StudentEntity studentA, studentB;

    @BeforeEach
    public void eachSetup(){
        LocalDateTime birthday = LocalDateTime.of(2003, 2, 6, 0, 0, 0);
        ZonedDateTime birthdayWithZone = birthday.atZone(ZoneId.systemDefault());
        CreateStudentDto studentDto = new CreateStudentDto("studentA", "010-2692-8532", "CyberSecurity"
                , birthdayWithZone.toInstant(), Degree.UNDERGRADUATE);
        studentA = studentService.createStudent(studentDto);
        studentDto = new CreateStudentDto("studentB", "010-1234-5678", "CyberSecurity",
                birthdayWithZone.toInstant(), Degree.UNDERGRADUATE);
        studentB = studentService.createStudent(studentDto);
    }

    @AfterEach
    void initialize(){
        studentCourseMappingRepository.deleteAll();
        studentRepository.deleteAll();
        courseRepository.deleteAll();
    }

    @Test
    @Transactional
    void testCreateCourse(){
        CreateCourseDto dto = new CreateCourseDto("자료구조", "정재윤", 50, 3, "SPRING", "CyberSecurity");
        CourseEntity course = courseService.createCourse(dto);
        course = courseRepository.findById(course.getId());
        assertEquals(50, course.getCourseSize());
        assertEquals("정재윤", course.getProfessor().getName());
    }

    @Test
    @Transactional
    void testRegisterCourse(){
        CreateCourseDto dto = new CreateCourseDto("자료구조", "정재윤", 50, 3,  "SPRING", "CyberSecurity");
        CourseEntity course = courseService.createCourse(dto);
        courseService.registerCourse(studentA.toStudent(), course.toCourse());
        List<StudentCourseMapping> mappings = studentCourseMappingRepository.findAll();
        assertEquals(1, mappings.size());
    }

    @Test
    @Transactional
    void testTwoStudentsRegisterMultiCourses(){
        CreateCourseDto dto = new CreateCourseDto("자료구조", "정재윤", 50, 3,  "SPRING", "CyberSecurity");
        CourseEntity courseA = courseService.createCourse(dto);
        dto = new CreateCourseDto("컴퓨터구조", "정재윤", 50, 3,  "SPRING", "CyberSecurity");
        CourseEntity courseB = courseService.createCourse(dto);
        courseService.registerCourse(studentA.toStudent(), courseA.toCourse());
        courseService.registerCourse(studentB.toStudent(), courseA.toCourse());
        courseService.registerCourse(studentA.toStudent(), courseB.toCourse());
        CourseEntity courseC = courseRepository.findById(courseA.getId());
        CourseEntity courseD = courseRepository.findById(courseB.getId());
        assertEquals(2, courseC.getNumOfReg());
        assertEquals(1, courseD.getNumOfReg());
        assertEquals(3, studentCourseMappingRepository.findAll().size());
    }

}