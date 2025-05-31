package com.example.studentmanagementsystem.course.entity;

import com.example.studentmanagementsystem.course.Course;
import com.example.studentmanagementsystem.member.Student;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentCourseMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public StudentCourseMapping(Course course, Student student, boolean isCompleted){
        this.course = course.toEntity();
        this.student = student.toEntity();
        this.isCompleted = isCompleted;
    }

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    boolean isCompleted;
}
