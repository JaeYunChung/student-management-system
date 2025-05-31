package com.example.studentmanagementsystem.library.repository;

import com.example.studentmanagementsystem.library.BookMemberMapping;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import com.example.studentmanagementsystem.security.Member;

import java.util.List;

public interface BookStudentMappingRepository {
    BookMemberMapping findById(Long id);
    List<BookMemberMapping> findAllByMember(Member member);
    BookMemberMapping save(BookMemberMapping book);
    void deleteAll();
}
