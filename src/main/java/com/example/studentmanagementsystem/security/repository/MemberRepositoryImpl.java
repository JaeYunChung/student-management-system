package com.example.studentmanagementsystem.security.repository;

import com.example.studentmanagementsystem.security.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository{
    private final JpaMemberRepository jpaMemberRepository;
    @Override
    public Member findByUsername(String username){
        return jpaMemberRepository.findByUsername(username);
    }

    @Override
    public Member save(Member member) {
        return jpaMemberRepository.save(member);
    }
}
