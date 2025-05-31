package com.example.studentmanagementsystem.member.domain;

import com.example.studentmanagementsystem.member.Professor;
import com.example.studentmanagementsystem.security.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    private Member member;

    private String phoneNumber;
    private Instant birthday;
    private Instant appointmentDate;

    @OneToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Professor toProfessor(){
        return Professor.builder()
                .id(id)
                .name(name)
                .member(member)
                .phoneNumber(phoneNumber)
                .birthday(birthday)
                .appointmentDate(appointmentDate)
                .department(department)
                .build();
    }
}
