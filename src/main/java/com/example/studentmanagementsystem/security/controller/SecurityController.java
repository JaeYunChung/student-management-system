package com.example.studentmanagementsystem.security.controller;

import com.example.studentmanagementsystem.security.Member;
import com.example.studentmanagementsystem.security.MemberRoleMapping;
import com.example.studentmanagementsystem.security.Role;
import com.example.studentmanagementsystem.security.dto.UserSignupRequestDto;
import com.example.studentmanagementsystem.security.service.MemberService;
import com.example.studentmanagementsystem.security.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SecurityController {
    private final MemberService memberService;
    private final RoleService roleService;
    @PostMapping("/signup")
    public ResponseEntity<Member> getUserData(@RequestBody UserSignupRequestDto dto){
        Member member = new Member(dto.email(), dto.password());
        Role role = roleService.findByRole("USER");
        MemberRoleMapping mapping = new MemberRoleMapping(member, role);
        member.appendRole(mapping);
        Member returnValue = memberService.saveMember(member);
        return ResponseEntity.status(HttpStatus.OK)
                .body(returnValue);
    }
}
