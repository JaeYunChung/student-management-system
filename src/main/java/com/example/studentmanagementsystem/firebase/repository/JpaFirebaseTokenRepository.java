package com.example.studentmanagementsystem.firebase.repository;

import com.example.studentmanagementsystem.firebase.domain.FirebaseToken;
import com.example.studentmanagementsystem.security.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFirebaseTokenRepository extends JpaRepository<FirebaseToken, Long> {
    FirebaseToken findByMember(Member member);
}
