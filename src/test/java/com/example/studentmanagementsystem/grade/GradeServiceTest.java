package com.example.studentmanagementsystem.grade;

import com.example.studentmanagementsystem.course.dto.CreateCourseDto;
import com.example.studentmanagementsystem.course.entity.*;
import com.example.studentmanagementsystem.course.repository.AssignmentRepository;
import com.example.studentmanagementsystem.course.repository.CourseRepository;
import com.example.studentmanagementsystem.course.repository.ExamRepository;
import com.example.studentmanagementsystem.course.repository.SubmissionRepository;
import com.example.studentmanagementsystem.course.service.AssignmentService;
import com.example.studentmanagementsystem.course.service.CourseService;
import com.example.studentmanagementsystem.course.service.ExamService;
import com.example.studentmanagementsystem.course.service.SubmissionService;
import com.example.studentmanagementsystem.event.EventCreateEntity;
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
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.EvaluationUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GradeServiceTest {

    @Autowired
    GradeService gradeService;

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

    @Autowired
    SubmissionRepository submissionRepository;

    @Autowired
    ExamRepository examRepository;

    StudentEntity student;
    CourseEntity course;
    @Autowired
    private ExamService examService;
    @Autowired
    private AssignmentService assignmentService;
    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @BeforeEach
    @Transactional
    void setup(){
        LocalDateTime birthday = LocalDateTime.of(2003, 2, 6, 0, 0, 0);
        ZonedDateTime birthdayWithZone = birthday.atZone(ZoneId.systemDefault());
        CreateStudentDto studentDto = new CreateStudentDto("studentA", "010-2692-8532", "CyberSecurity"
                , birthdayWithZone.toInstant(), Degree.UNDERGRADUATE);
        student = studentService.createStudent(studentDto);
        CreateCourseDto dto = new CreateCourseDto("자료구조", "정재윤", 50, 3, "SPRING", "CyberSecurity");
        course = courseService.createCourse(dto);
    }

    @AfterEach // 모든 관련된 테이블을 지워야한다.
    @Transactional
    void initialize(){// 먼저 제출물 삭제
        submissionRepository.deleteAll();
        examRepository.deleteAll();        // 시험 기록 삭제
        assignmentRepository.deleteAll();  // 과제 삭제
        gradeRepository.deleteAll();       // 성적 삭제
        studentRepository.deleteAll();     // 마지막으로 학생 삭제
        courseRepository.deleteAll();      // 과목 삭제
    }

    @Test
    @Transactional
    void testCreateExamEntity(){
        examService.createExamEntity(course, student, 80.0, 40);
        List<ExamEntity> entity = examRepository.findByCourseAndStudent(course, student);
        assertEquals(1, entity.size());
    }



    @Test
    @Transactional
    void testSendSubmission(){
        AssignmentEntity assign1 = assignmentService.createAssignmentEntity(course, "content1");
        AssignmentEntity assign2 = assignmentService.createAssignmentEntity(course, "content2");
        SubmissionEntity submission1 = submissionService.sendSubmission("content1", course, assign1, student, 100, 10);
        SubmissionEntity submission2 = submissionService.sendSubmission("content2", course, assign2, student, 90, 10);
        List<SubmissionEntity> entities = submissionRepository.findByCourseAndStudent(course, student);
        assertEquals(2, entities.size());
    }

    @Test
    void testPublisherListener(){
        EvaluationEntity entity = EvaluationEntity.builder()
                .course(course)
                .student(student)
                .score(20.0)
                .ratio(40)
                .build();
        eventPublisher.publishEvent(new EventCreateEntity(entity));
        assertEquals(1, gradeRepository.findAll().size());
    }

    @Test
    @Transactional
    void testCreateGradeAndUpdateGrade() {

        ExamEntity midExam = examService.createExamEntity(course, student, 80.0, 40);
        ExamEntity finalExam = examService.createExamEntity(course, student, 90.0, 40);
        AssignmentEntity assign1 = assignmentService.createAssignmentEntity(course, "content1");
        AssignmentEntity assign2 = assignmentService.createAssignmentEntity(course, "content2");
        SubmissionEntity submission1 = submissionService.sendSubmission("content1", course, assign1, student, 100, 10);
        SubmissionEntity submission2 = submissionService.sendSubmission("content2", course, assign2, student, 90, 10);
        Optional<GradeEntity> grade = gradeRepository.findByCourseAndStudent(course, student);
        if (grade.isEmpty()){
            throw new IllegalArgumentException("해당 학생의 점수가 없습니다.");
        }
        assertEquals(87.0, grade.get().getGrade());
//        finalExam.setScore(80.0);
//        examRepository.save(finalExam);
//        grade = gradeRepository.findByCourseAndStudent(course, student);
//        if (grade.isEmpty()){
//            throw new IllegalArgumentException("해당 학생의 점수가 없습니다.");
//        }
//        assertEquals(83.0, grade.get().getGrade());
    }

    @Test
    @Transactional
    void testScoreAndAdjustFinalGrade(){
        createMultipleStudentsAndGrades(course);
        gradeService.createRelativeGrade(course.toCourse());
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
        double grade = gradeRepository.findByCourseAndStudent(course, student).get().getGrade();
        gradeService.adjustFinalScore(course.toCourse(), student.toStudent());
        double adjustedGrade = gradeRepository.findByCourseAndStudent(course, student).get().getGrade();
        assertEquals(grade + 0.5, adjustedGrade);
    }

    @Transactional
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