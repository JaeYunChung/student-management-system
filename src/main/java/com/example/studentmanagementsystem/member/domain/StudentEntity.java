package com.example.studentmanagementsystem.member.domain;

import com.example.studentmanagementsystem.course.Semester;
import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.course.entity.StudentCourseMapping;
import com.example.studentmanagementsystem.dormitory.domain.DormitoryEntity;
import com.example.studentmanagementsystem.grade.GradeEntity;
import com.example.studentmanagementsystem.graduation.EnglishExamEntity;
import com.example.studentmanagementsystem.member.Degree;
import com.example.studentmanagementsystem.member.Student;
import com.example.studentmanagementsystem.security.Member;
import com.example.studentmanagementsystem.utils.PositiveIntegerCounter;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "dorm_id")
    @Nullable
    private DormitoryEntity dormitory;

    @OneToOne(cascade = CascadeType.ALL)
    private Member member;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private Instant enterAt;
    private Instant birthday;
    private int grade;

    @Enumerated(EnumType.STRING)
    private Semester semester;

    @Enumerated(EnumType.STRING)
    private Degree degree;

    private Double totalGrade;

    private int totalCredit;

    private boolean scholarshipStatus;


    public Student toStudent(){
        return Student.builder()
                .id(id)
                .name(name)
                .dormitory(dormitory == null ? null : dormitory.toDormitory())
                .member(member)
                .phoneNumber(phoneNumber)
                .department(department)
                .enterAt(enterAt)
                .birthday(birthday)
                .grade(new PositiveIntegerCounter(grade))
                .degree(degree)
                .semester(semester)
                .totalGrade(totalGrade)
                .totalCredit(totalCredit)
                .scholarshipStatus(scholarshipStatus)
                .build();
    }
}
