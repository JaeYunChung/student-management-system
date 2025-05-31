package com.example.studentmanagementsystem.grade;

import com.example.studentmanagementsystem.course.dto.CreateCourseDto;
import com.example.studentmanagementsystem.course.entity.AssignmentEntity;
import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.course.entity.ExamEntity;
import com.example.studentmanagementsystem.course.entity.SubmissionEntity;
import com.example.studentmanagementsystem.course.repository.AssignmentRepository;
import com.example.studentmanagementsystem.course.repository.CourseRepository;
import com.example.studentmanagementsystem.course.repository.ExamRepository;
import com.example.studentmanagementsystem.course.repository.SubmissionRepository;
import com.example.studentmanagementsystem.course.service.CourseService;
import com.example.studentmanagementsystem.member.Degree;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import com.example.studentmanagementsystem.member.dto.CreateStudentDto;
import com.example.studentmanagementsystem.member.repository.StudentRepository;
import com.example.studentmanagementsystem.member.service.StudentService;
import net.bytebuddy.dynamic.scaffold.subclass.SubclassImplementationTarget;
import org.hibernate.annotations.NaturalId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GradeServiceTest {

    @Autowired
    GradeService gradeService;

    @Autowired
    ExamRepository examRepository;

    @Autowired
    SubmissionRepository submissionRepository;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    AssignmentRepository assignmentRepository;

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
    }

    @AfterEach
    void initialize(){
        gradeRepository.deleteAll();
        assignmentRepository.deleteAll();
        studentRepository.deleteAll();
        courseRepository.deleteAll();
    }

    @Test
    @Transactional
    void testCreateGrade(){
        ExamEntity midExam = createExamEntity(course, student, 80.0, 40);
        ExamEntity finalExam = createExamEntity(course, student, 90.0, 40);
        AssignmentEntity assign1 = createAssignmentEntity(course, "content1");
        AssignmentEntity assign2 = createAssignmentEntity(course, "content2");
        SubmissionEntity submission1 = createSubmissionEntity("content1", course, assign1, student, 100, 10);
        SubmissionEntity submission2 = createSubmissionEntity("content2", course, assign2, student, 90, 10);
        gradeService.createGrades(student.toStudent(), course.toCourse());
        GradeEntity grade = gradeRepository.findByCourseAndStudent(course, student);
        assertEquals(87.0, grade.getGrade());

    }

    @Test
    @Transactional
    void testScoreAndAdjustFinalGrade(){
        createMultipleStudentsAndGrades(course);
        gradeService.scoreFinalGrade(course.toCourse());
        List<GradeEntity> grades = gradeRepository.findAllByCourse(course)
                .stream()
                .sorted((o1, o2) -> {
                    double score = o1.getGrade() - o2.getGrade();
                    if (score < 0) return -1;
                    else if (score > 0) return 1;
                    else return 0;
                })
                .toList();
        assertEquals(4.0, grades.get(98).getGrade());
        assertEquals(2.0, grades.get(11).getGrade());
        double grade = gradeRepository.findByCourseAndStudent(course, student).getGrade();
        gradeService.adjustFinalScore(course.toCourse(), student.toStudent());
        double adjustedGrade = gradeRepository.findByCourseAndStudent(course, student).getGrade();
        assertEquals(grade + 0.5, adjustedGrade);
    }
    ExamEntity createExamEntity(CourseEntity course, StudentEntity student, double grade, int ratio){
        ExamEntity exam = ExamEntity.builder()
                .course(course)
                .student(student)
                .grade(grade)
                .ratio(ratio)
                .build();
        return examRepository.save(exam);
    }
    SubmissionEntity createSubmissionEntity(String contentUrl, CourseEntity course, AssignmentEntity assignment, StudentEntity student, int grade, int ratio)
    {
        SubmissionEntity submission = SubmissionEntity.builder()
                .contentUrl(contentUrl)
                .course(course)
                .assignment(assignment)
                .student(student)
                .grade(grade)
                .ratio(ratio)
                .build();
        return submissionRepository.save(submission);
    }
    AssignmentEntity createAssignmentEntity(CourseEntity course, String contentUrl){
        AssignmentEntity assignment = AssignmentEntity.builder()
                .course(course)
                .contentUrl(contentUrl)
                .createdAt(Instant.now())
                .limitAt(Instant.now().plus(5, ChronoUnit.DAYS))
                .build();
        return assignmentRepository.save(assignment);
    }

    void createMultipleStudentsAndGrades(CourseEntity course){
        Random random = new Random();
        for (int i = 1; i <= 99; i++){
            String name = "student" + i;
            LocalDateTime birthday = LocalDateTime.of(2003, 2, 6, 0, 0, 0);
            ZonedDateTime birthdayWithZone = birthday.atZone(ZoneId.systemDefault());
            CreateStudentDto studentDto = new CreateStudentDto(name, "010-2692-8532", "CyberSecurity"
                    , birthdayWithZone.toInstant(), Degree.UNDERGRADUATE);
            student = studentService.createStudent(studentDto);
            Double gradeValue = random.nextDouble() * 100;
            Grade grade = Grade.builder()
                    .student(student.toStudent())
                    .course(course.toCourse())
                    .grade(gradeValue)
                    .build();
            gradeRepository.save(grade.toEntity());
        }
    }
}