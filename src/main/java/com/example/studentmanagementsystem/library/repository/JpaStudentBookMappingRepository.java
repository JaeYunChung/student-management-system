package com.example.studentmanagementsystem.library.repository;

import com.example.studentmanagementsystem.library.BookMemberMapping;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import com.example.studentmanagementsystem.security.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaStudentBookMappingRepository extends JpaRepository<BookMemberMapping, Long> {
    List<BookMemberMapping> findAllByMember(Member member);
}
