package com.example.studentmanagementsystem.grade;

import com.example.studentmanagementsystem.course.Course;
import com.example.studentmanagementsystem.member.Student;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Grade {
    private Long id;
    private Student student;
    private Course course;
    private Double grade;

    public GradeEntity toEntity(){
        return GradeEntity.builder()
                .id(id)
                .student(student.toEntity())
                .course(course.toEntity())
                .grade(grade)
                .build();
    }
}
