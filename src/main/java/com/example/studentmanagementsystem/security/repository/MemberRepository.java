package com.example.studentmanagementsystem.security.repository;

import com.example.studentmanagementsystem.security.Member;

public interface MemberRepository {
    Member findByUsername(String username);
    Member save(Member member);
}
