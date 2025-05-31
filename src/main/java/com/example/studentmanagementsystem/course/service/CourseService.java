package com.example.studentmanagementsystem.course.service;

import com.example.studentmanagementsystem.course.Course;
import com.example.studentmanagementsystem.course.dto.CreateCourseDto;
import com.example.studentmanagementsystem.course.Semester;
import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.course.entity.StudentCourseMapping;
import com.example.studentmanagementsystem.course.repository.CourseRepository;
import com.example.studentmanagementsystem.course.repository.StudentCourseMappingRepository;
import com.example.studentmanagementsystem.member.Student;
import com.example.studentmanagementsystem.member.domain.Department;
import com.example.studentmanagementsystem.member.domain.ProfessorEntity;
import com.example.studentmanagementsystem.member.repository.DepartmentRepository;
import com.example.studentmanagementsystem.member.repository.ProfessorRepository;
import com.example.studentmanagementsystem.member.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final ProfessorRepository professorRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final StudentCourseMappingRepository studentCourseMappingRepository;

    @Transactional
    public CourseEntity createCourse(CreateCourseDto dto){
        ProfessorEntity professor = professorRepository.findByName(dto.professorName())
                .orElseThrow(() -> new IllegalArgumentException("해당 성함의 교수님이 존재하지 않습니다"));
        Department department = departmentRepository.findByName(dto.departmentName())
                .orElseThrow(() -> new IllegalArgumentException("해당 학과가 존재하지 않습니다."));
        CourseEntity courseEntity = CourseEntity.builder()
                .name(dto.name())
                .professor(professor)
                .courseSize(dto.courseSize())
                .credit(dto.credit())
                .numOfReg(0)
                .spareSize(dto.courseSize())
                .semester(Semester.valueOf(dto.semester()))
                .department(department)
                .build();
        return courseRepository.save(courseEntity);
    }

    @Transactional
    public void registerCourse(Student student, Course course){
        course.registerCourse();
        StudentCourseMapping studentCourseMapping = new StudentCourseMapping(course, student, false);
        studentCourseMappingRepository.save(studentCourseMapping);
        studentRepository.save(student);
        courseRepository.save(course.toEntity());
    }

}
