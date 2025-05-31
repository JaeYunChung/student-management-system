package com.example.studentmanagementsystem.member.domain;

import com.example.studentmanagementsystem.member.TypeOfCourse;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class DepartmentCourseMapping {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private int enterYear;

    private String courseName;

    @Enumerated(EnumType.STRING)
    private TypeOfCourse type;
}
