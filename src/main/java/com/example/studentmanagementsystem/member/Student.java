package com.example.studentmanagementsystem.member;

import com.example.studentmanagementsystem.course.Course;
import com.example.studentmanagementsystem.course.Semester;
import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.course.entity.StudentCourseMapping;
import com.example.studentmanagementsystem.dormitory.Dormitory;
import com.example.studentmanagementsystem.grade.Grade;
import com.example.studentmanagementsystem.grade.GradeEntity;
import com.example.studentmanagementsystem.graduation.EnglishExam;
import com.example.studentmanagementsystem.member.domain.Department;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import com.example.studentmanagementsystem.security.Member;
import com.example.studentmanagementsystem.utils.PositiveIntegerCounter;
import lombok.*;

import java.time.*;
import java.util.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Long id;
    private String name;
    private Member member;
    private String phoneNumber;
    private Department department;
    private Instant enterAt;
    private Instant birthday;
    private PositiveIntegerCounter grade;
    private Semester semester;
    private Degree degree;
    private int totalCredit;
    private Double totalGrade;
    private boolean scholarshipStatus;
    private Dormitory dormitory;

    public Student(String name, String phoneNumber, Department department,
                   Instant birthday, Degree degree){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.department = department;
        LocalDate nowDate = LocalDate.of(LocalDate.now().getYear(), 3, 2);
        this.enterAt = nowDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        this.birthday = birthday;
        this.grade = new PositiveIntegerCounter();
        this.semester = Semester.SPRING;
        this.degree = degree;
        this.totalCredit = 0;
        this.totalGrade = 0.D;
        this.scholarshipStatus = false;
    }
    public void signUpPortal(Member member){
        this.member = member;
    }

    public void registerDormitory(Dormitory dormitory){
        this.dormitory = dormitory;
    }

    public void promoteGrade(){
        this.getGrade().increase();
    }

    public StudentEntity toEntity(){

        StudentEntity studentEntity = StudentEntity.builder()
                .id(id)
                .name(name)
                .member(member)
                .phoneNumber(phoneNumber)
                .department(department)
                .enterAt(enterAt)
                .birthday(birthday)
                .grade(grade.getValue())
                .semester(semester)
                .degree(degree)
                .totalGrade(totalGrade)
                .totalCredit(totalCredit)
                .scholarshipStatus(scholarshipStatus)
                .build();
        if (dormitory != null){
            studentEntity.setDormitory(dormitory.toEntity());
        }

        return studentEntity;
    }
}
