package com.example.studentmanagementsystem.member.domain;

import com.example.studentmanagementsystem.graduation.EnglishExamEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long regCost;

    private int minCredit;

    private int optionRequiredCourseMinCredit;

    private int minGrade;


}
