package com.example.studentmanagementsystem.library.repository;

import com.example.studentmanagementsystem.library.BookMemberMapping;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import com.example.studentmanagementsystem.security.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookStudentMappingRepositoryImpl implements BookStudentMappingRepository {
    private final  JpaStudentBookMappingRepository jpaStudentBookMappingRepository;

    @Override
    public BookMemberMapping findById(Long id) {
        return jpaStudentBookMappingRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 도서가 존재하지 않습니다."));
    }

    @Override
    public List<BookMemberMapping> findAllByMember(Member member) {
        return jpaStudentBookMappingRepository.findAllByMember(member);
    }

    @Override
    public BookMemberMapping save(BookMemberMapping book) {
        return jpaStudentBookMappingRepository.save(book);
    }

    @Override
    public void deleteAll() {
        jpaStudentBookMappingRepository.deleteAll();
    }
}
