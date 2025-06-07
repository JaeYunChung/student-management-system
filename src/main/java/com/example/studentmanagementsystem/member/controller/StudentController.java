package com.example.studentmanagementsystem.member.controller;

import com.example.studentmanagementsystem.member.Student;
import com.example.studentmanagementsystem.member.service.StudentService;
import com.example.studentmanagementsystem.security.Member;
import com.example.studentmanagementsystem.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;
    private final MemberRepository memberRepository;

    @GetMapping("/me")
    public ResponseEntity<Student> getMyInfo(Principal principal){
        Member member = memberRepository.findByUsername(principal.getName());
        Student student = studentService.findByMember(member);
        return ResponseEntity.ok(student);
    }
}
