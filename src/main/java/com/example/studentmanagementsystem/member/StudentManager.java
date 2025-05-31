package com.example.studentmanagementsystem.member;

import com.example.studentmanagementsystem.member.domain.Department;
import com.example.studentmanagementsystem.security.Member;

import java.time.Instant;

public class StudentManager {
    private Long id;
    private String name;
    private String phoneNumber;
    private Member member;
    private Instant enterAt;
    private Department department;
    private Instant birthday;
    private Long salary;
}
