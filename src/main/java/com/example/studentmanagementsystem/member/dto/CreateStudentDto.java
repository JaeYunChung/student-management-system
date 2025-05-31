package com.example.studentmanagementsystem.member.dto;

import com.example.studentmanagementsystem.dormitory.TypeOfDorm;
import com.example.studentmanagementsystem.member.Degree;

import java.time.Instant;

public record CreateStudentDto(String name, String phoneNumber, String departmentName,
                               Instant birthday, Degree degree) {
}