package com.example.studentmanagementsystem.member;

import com.example.studentmanagementsystem.member.domain.Department;
import com.example.studentmanagementsystem.member.domain.ProfessorEntity;
import com.example.studentmanagementsystem.security.Member;
import lombok.Builder;

import java.time.Instant;

@Builder
public class Professor {
    private Long id;
    private String name;
    private Member member;
    private String phoneNumber;
    private Instant birthday;
    private Instant appointmentDate;
    private Department department;

    public ProfessorEntity toEntity(){
        return ProfessorEntity.builder()
                .id(id)
                .member(member)
                .phoneNumber(phoneNumber)
                .birthday(birthday)
                .appointmentDate(appointmentDate)
                .department(department)
                .build();
    }
}
