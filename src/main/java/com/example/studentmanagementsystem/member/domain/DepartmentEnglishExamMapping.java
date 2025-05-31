package com.example.studentmanagementsystem.member.domain;

import com.example.studentmanagementsystem.graduation.EnglishExam;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class DepartmentEnglishExamMapping {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Enumerated(EnumType.STRING)
    private EnglishExam type;

    private int score;

}
