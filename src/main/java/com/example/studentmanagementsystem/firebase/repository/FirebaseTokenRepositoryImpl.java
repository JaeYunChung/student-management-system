package com.example.studentmanagementsystem.firebase.repository;

import com.example.studentmanagementsystem.firebase.domain.FirebaseToken;
import com.example.studentmanagementsystem.security.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FirebaseTokenRepositoryImpl implements FirebaseTokenRepository{

    private final JpaFirebaseTokenRepository jpaFirebaseTokenRepository;

    @Override
    public FirebaseToken saveToken(FirebaseToken token) {
        return jpaFirebaseTokenRepository.save(token);
    }

    @Override
    public FirebaseToken findByMember(Member member) {
        return jpaFirebaseTokenRepository.findByMember(member);
    }
}
