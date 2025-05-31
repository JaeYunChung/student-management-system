package com.example.studentmanagementsystem.member.service;

import com.example.studentmanagementsystem.course.Semester;
import com.example.studentmanagementsystem.course.dto.CreateCourseDto;
import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.course.entity.StudentCourseMapping;
import com.example.studentmanagementsystem.course.repository.CourseRepository;
import com.example.studentmanagementsystem.course.repository.StudentCourseMappingRepository;
import com.example.studentmanagementsystem.course.service.CourseService;
import com.example.studentmanagementsystem.grade.GradeEntity;
import com.example.studentmanagementsystem.grade.GradeRepository;
import com.example.studentmanagementsystem.member.Degree;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import com.example.studentmanagementsystem.member.dto.CreateStudentDto;
import com.example.studentmanagementsystem.member.repository.StudentRepository;
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
class StudentServiceTest {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentCourseMappingRepository studentCourseMappingRepository;

    StudentEntity student;
    CourseEntity course;

    @BeforeEach
    void setup(){
        LocalDateTime birthday = LocalDateTime.of(2003, 2, 6, 0, 0, 0);
        ZonedDateTime birthdayWithZone = birthday.atZone(ZoneId.systemDefault());
        CreateStudentDto studentDto = new CreateStudentDto("studentA", "010-2692-8532", "CyberSecurity"
                , birthdayWithZone.toInstant(), Degree.UNDERGRADUATE);
        student = studentService.createStudent(studentDto);
        CreateCourseDto dto = new CreateCourseDto("자료구조", "정재윤", 50, 3, "SPRING", "CyberSecurity");
        course = courseService.createCourse(dto);
        courseService.registerCourse(student.toStudent(), course.toCourse());
    }
    @AfterEach
    @Transactional
    void initialize(){
        gradeRepository.deleteAll();
        studentCourseMappingRepository.deleteAll();
        studentRepository.deleteAll();
        courseRepository.deleteAll();
    }


    @Test
    @Transactional
    void testPromoteStudentForMidYear(){
        assertEquals(0, student.getTotalCredit());
        assertEquals(0, student.getTotalGrade());
        List<StudentCourseMapping> courseMappings = studentCourseMappingRepository.findAllByStudent(student);
        assertFalse( courseMappings.get(0).isCompleted());
        GradeEntity gradeEntity = createGradeEntity(student, course, 4.5);
        studentService.promoteStudentForMidYear();
        StudentEntity savedStudent = studentRepository.findById(student.getId());
        courseMappings = studentCourseMappingRepository.findAllByStudent(student);
        assertTrue(courseMappings.get(0).isCompleted());
        assertEquals(Semester.AUTUMN, savedStudent.getSemester());
        assertEquals(4.5, savedStudent.getTotalGrade());
        assertEquals(3, savedStudent.getTotalCredit());
        assertEquals(1, savedStudent.getGrade());
    }

    public GradeEntity createGradeEntity(StudentEntity student, CourseEntity course, double grade){
        GradeEntity gradeEntity = GradeEntity.builder()
                .student(student)
                .course(course)
                .grade(grade)
                .build();
        gradeRepository.save(gradeEntity);
        return gradeEntity;
    }
}