package com.example.studentmanagementsystem.firebase.controller;

import com.example.studentmanagementsystem.firebase.domain.FirebaseToken;
import com.example.studentmanagementsystem.firebase.service.FirebaseService;
import com.example.studentmanagementsystem.security.Member;
import com.example.studentmanagementsystem.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FirebaseController {

    private final FirebaseService firebaseService;
    private final MemberRepository memberRepository;

    @PostMapping("/fcm/saveToken")
    public ResponseEntity<FirebaseToken> saveToken(@RequestBody Map<String, String> tokenMap, @AuthenticationPrincipal Principal principal){
        Member member = memberRepository.findByUsername(principal.getName());
        FirebaseToken firebaseToken = new FirebaseToken(member, tokenMap.get("token"));
        FirebaseToken returnValue = firebaseService.saveToken(firebaseToken);
        log.info("토큰이 저장됨");
        return ResponseEntity.status(HttpStatus.OK)
                .body(returnValue);
    }
}
