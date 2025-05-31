package com.example.studentmanagementsystem.security.repository;

import com.example.studentmanagementsystem.security.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {
    Member findByUsername(String username);
}
