package com.example.studentmanagementsystem.firebase.repository;

import com.example.studentmanagementsystem.firebase.domain.FirebaseToken;
import com.example.studentmanagementsystem.security.Member;

public interface FirebaseTokenRepository {
    FirebaseToken saveToken(FirebaseToken token);
    FirebaseToken findByMember(Member member);
}
