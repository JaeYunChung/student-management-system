package com.example.studentmanagementsystem.graduation;

import com.example.studentmanagementsystem.course.Course;
import com.example.studentmanagementsystem.course.dto.CreateCourseDto;
import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.course.repository.CourseRepository;
import com.example.studentmanagementsystem.course.repository.StudentCourseMappingRepository;
import com.example.studentmanagementsystem.course.service.CourseService;
import com.example.studentmanagementsystem.grade.Grade;
import com.example.studentmanagementsystem.grade.GradeRepository;
import com.example.studentmanagementsystem.graduation.repository.EnglishExamRepository;
import com.example.studentmanagementsystem.graduation.service.EnglishExamService;
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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GraduateServiceTest {

    @Autowired
    GraduateService graduateService;

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentCourseMappingRepository studentCourseMappingRepository;

    @Autowired
    EnglishExamService englishExamService;

    @Autowired
    EnglishExamRepository englishExamRepository;

    StudentEntity student;

    @BeforeEach
    void setup(){
        LocalDateTime birthday = LocalDateTime.of(2003, 2, 6, 0, 0, 0);
        ZonedDateTime birthdayWithZone = birthday.atZone(ZoneId.systemDefault());
        CreateStudentDto studentDto = new CreateStudentDto("studentA", "010-2692-8532", "CyberSecurity"
                , birthdayWithZone.toInstant(), Degree.UNDERGRADUATE);
        student = studentService.createStudent(studentDto);
    }

    @AfterEach
    void initialize(){
        gradeRepository.deleteAll();
        studentCourseMappingRepository.deleteAll();
        englishExamRepository.deleteAll();
        studentRepository.deleteAll();
        courseRepository.deleteAll();
    }

    @Test
    void testGraduateStudentAndTrue(){
        CourseEntity courseA = createAndRegisterCourseEntity(student, "자료구조", 3);
        CourseEntity courseB =  createAndRegisterCourseEntity(student, "데이터베이스", 3);
        createGradeEntity(student, courseA, 4.0);
        createGradeEntity(student, courseB, 2.0);
        studentService.promoteStudentForMidYear();
        englishExamService.submitEnglishExamScore(student.toStudent(), EnglishExam.TOEIC, 800);
        student = studentRepository.findById(student.getId());
        assertTrue(graduateService.canGraduate(student.toStudent()));
    }

    @Test
    void testGraduateStudentAndFalseByLowGrade(){
        CourseEntity courseA = createAndRegisterCourseEntity(student, "자료구조", 3);
        CourseEntity courseB =  createAndRegisterCourseEntity(student, "데이터베이스", 3);
        createGradeEntity(student, courseA, 2.0);
        createGradeEntity(student, courseB, 2.0);
        studentService.promoteStudentForMidYear();
        englishExamService.submitEnglishExamScore(student.toStudent(), EnglishExam.TOEIC, 800);
        student = studentRepository.findById(student.getId());
        assertFalse(graduateService.canGraduate(student.toStudent()));
    }

    @Test
    void testGraduateStudentAndFalseByNotTakenRequiredCourses(){
        CourseEntity courseA = createAndRegisterCourseEntity(student, "컴퓨터구조", 3);
        CourseEntity courseB =  createAndRegisterCourseEntity(student, "데이터베이스", 3);
        createGradeEntity(student, courseA, 4.0);
        createGradeEntity(student, courseB, 2.0);
        studentService.promoteStudentForMidYear();
        englishExamService.submitEnglishExamScore(student.toStudent(), EnglishExam.TOEIC, 800);
        student = studentRepository.findById(student.getId());
        assertFalse(graduateService.canGraduate(student.toStudent()));
    }

    CourseEntity createAndRegisterCourseEntity(StudentEntity student, String courseName, int credit){
        String departmentName = "CyberSecurity";
        CreateCourseDto dto = new CreateCourseDto(courseName, "정재윤", 50, credit, "SPRING", departmentName);
        CourseEntity savedCourse = courseService.createCourse(dto);
        courseService.registerCourse(student.toStudent(), savedCourse.toCourse());
        return savedCourse;
    }

    void createGradeEntity(StudentEntity student, CourseEntity course, double score){
        Grade grade = Grade.builder()
                .student(student.toStudent())
                .course(course.toCourse())
                .grade(score)
                .build();
        gradeRepository.save(grade.toEntity());
    }

}