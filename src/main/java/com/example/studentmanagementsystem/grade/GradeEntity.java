package com.example.studentmanagementsystem.grade;

import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.event.GradeEventListener;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    private Double grade;

    public Grade toGrade(){
        return Grade.builder()
                .student(student.toStudent())
                .course(course.toCourse())
                .grade(grade)
                .build();
    }
}
