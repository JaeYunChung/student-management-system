package com.example.studentmanagementsystem.member.service;

import com.example.studentmanagementsystem.course.Course;
import com.example.studentmanagementsystem.course.Semester;
import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.course.entity.StudentCourseMapping;
import com.example.studentmanagementsystem.course.repository.StudentCourseMappingRepository;
import com.example.studentmanagementsystem.grade.GradeEntity;
import com.example.studentmanagementsystem.grade.GradeRepository;
import com.example.studentmanagementsystem.member.domain.Department;
import com.example.studentmanagementsystem.member.Student;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import com.example.studentmanagementsystem.member.dto.CreateStudentDto;
import com.example.studentmanagementsystem.member.repository.DepartmentRepository;
import com.example.studentmanagementsystem.member.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class StudentService {

    private final DepartmentRepository departmentRepository;
    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;
    private final StudentCourseMappingRepository studentCourseMappingRepository;

    @Transactional
    public StudentEntity createStudent(CreateStudentDto dto){
        Department department = departmentRepository.findByName(dto.departmentName())
                .orElseThrow(() -> new IllegalArgumentException("해당 부서가 없습니다."));
        Student student = new Student(dto.name(), dto.phoneNumber(), department, dto.birthday(), dto.degree());
        student.promoteGrade();
        return studentRepository.save(student);
    }

    @Transactional
    public void saveStudent(Student student){
        studentRepository.save(student);
    }

    @Transactional
    public List<Student> findAllStudents(){
        return studentRepository.findAll()
                .stream()
                .map(StudentEntity::toStudent)
                .toList();
    }

    @Transactional
    @Scheduled(cron = "0 0 0 1 1 *")
    public void promoteStudentForNewYear(){
        List<Student> students = findAllStudents();
        students.forEach(this::promoteGradeAndUpdateGrade);
        List<StudentEntity> studentEntities = students.stream()
                .map(Student::toEntity)
                .toList();
        studentRepository.saveAll(studentEntities);
    }

    @Transactional
    @Scheduled(cron = "0 0 0 1 7 *")
    public void promoteStudentForMidYear(){
        List<Student> students = findAllStudents();
        students.forEach(this::promoteGradeAndUpdateGrade);
        List<StudentEntity> studentEntities = students.stream()
                .map(Student::toEntity)
                .toList();
        studentRepository.saveAll(studentEntities);
    }

    @Transactional
    public List<Course> getAdmittedCourses(Student student){
        return gradeRepository.findAllByStudent(student.toEntity())
                .stream()
                .filter(each -> each.getGrade() > 0)
                .map(GradeEntity::getCourse)
                .map(CourseEntity::toCourse)
                .toList();
    }

    // 총학점을 계산하고 점수를 갱신한다.
    public Double calcTotalGrade(Student student){
        int totalCredit = calcTotalCredit(student);
        List<GradeEntity> grades= gradeRepository.findAllByStudent(student.toEntity());
        double totalGrade = grades.stream()
                .mapToDouble(each -> each.getGrade() * (each.getCourse().getCredit() / (float) totalCredit))
                .sum();
        return totalGrade;
    }

    // 현재 학년과 학점을 갱신한다.
    public void promoteGradeAndUpdateGrade(Student student){
        student.setTotalGrade(calcTotalGrade(student));
        if (student.getSemester().equals(Semester.SPRING))
            student.setSemester(Semester.AUTUMN);
        else{
            student.setSemester(Semester.SPRING);
            student.promoteGrade();
        }
    }

    public List<Course> getCompletedCourses(Student student){
        return studentCourseMappingRepository.findAllByStudent(student.toEntity())
                .stream()
                .filter(StudentCourseMapping::isCompleted)
                .map(StudentCourseMapping::getCourse)
                .map(CourseEntity::toCourse)
                .toList();
    }

    public List<Course> getCurrentCourses(Student student){
        return studentCourseMappingRepository.findAllByStudent(student.toEntity())
                .stream()
                .filter(each -> !each.isCompleted())
                .map(StudentCourseMapping::getCourse)
                .map(CourseEntity::toCourse)
                .toList();
    }

    public void alterCoursesCompletedFlag(Student student){
        List<StudentCourseMapping> mappings = studentCourseMappingRepository.findAllByStudent(student.toEntity())
                .stream()
                .filter(each -> !each.isCompleted())
                .peek(each -> each.setCompleted(true))
                .toList();
        studentCourseMappingRepository.saveAll(mappings);
    }

    public int calcTotalCredit(Student student){
        alterCoursesCompletedFlag(student);
        int totalCredit = getCompletedCourses(student)
                .stream()
                .mapToInt(Course::getCredit)
                .sum();
        student.setTotalCredit(totalCredit);
        return totalCredit;
    }

}
