package com.example.studentmanagementsystem.course;

import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.member.Professor;
import com.example.studentmanagementsystem.member.domain.Department;
import com.example.studentmanagementsystem.utils.PositiveIntegerCounter;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class Course {
    private Long id;
    private String name;
    private Professor professor;
    private int courseSize;
    private int credit;
    private PositiveIntegerCounter numOfReg;
    private PositiveIntegerCounter spareSize;
    private Semester semester;
    private Department department;
    private int openYear;

    // 수강신청
    public void registerCourse(){
        numOfReg.increase();
        spareSize.decrease();
    }

    public CourseEntity toEntity(){
        return CourseEntity.builder()
                .id(id)
                .name(name)
                .professor(professor.toEntity())
                .courseSize(courseSize)
                .credit(credit)
                .numOfReg(numOfReg.getValue())
                .spareSize(spareSize.getValue())
                .semester(semester)
                .openYear(openYear)
                .department(department)
                .build();
    }
}
