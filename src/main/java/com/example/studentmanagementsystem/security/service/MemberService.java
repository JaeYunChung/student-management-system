package com.example.studentmanagementsystem.security.service;

import com.example.studentmanagementsystem.security.Member;
import com.example.studentmanagementsystem.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public Member saveMember(Member member){
        return memberRepository.save(member);
    }
}
