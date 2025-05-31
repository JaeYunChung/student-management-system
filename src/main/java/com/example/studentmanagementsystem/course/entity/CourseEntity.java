package com.example.studentmanagementsystem.course.entity;

import com.example.studentmanagementsystem.course.Course;
import com.example.studentmanagementsystem.course.Semester;
import com.example.studentmanagementsystem.member.domain.Department;
import com.example.studentmanagementsystem.member.domain.ProfessorEntity;
import com.example.studentmanagementsystem.utils.PositiveIntegerCounter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private ProfessorEntity professor;


    private int courseSize;
    private int credit;
    private int numOfReg;
    private int spareSize;
    private int openYear;

    @Enumerated(EnumType.STRING)
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Course toCourse(){
        return Course.builder()
                .id(id)
                .name(name)
                .professor(professor.toProfessor())
                .courseSize(courseSize)
                .credit(credit)
                .numOfReg(new PositiveIntegerCounter(numOfReg))
                .spareSize(new PositiveIntegerCounter(spareSize))
                .openYear(openYear)
                .semester(semester)
                .build();
    }
}
