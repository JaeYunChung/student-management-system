package com.example.studentmanagementsystem.firebase.domain;

import com.example.studentmanagementsystem.member.Student;
import com.example.studentmanagementsystem.security.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class FirebaseToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public FirebaseToken(Member member, String token){
        this.member = member;
        this.token = token;
    }

    @OneToOne
    private Member member;

    private String token;
}
