package com.example.studentmanagementsystem.course.entity;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class EvaluationEntity {

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    private double score;
    private int ratio;
}
