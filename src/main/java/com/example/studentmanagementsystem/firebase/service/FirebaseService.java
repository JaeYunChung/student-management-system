package com.example.studentmanagementsystem.firebase.service;

import com.example.studentmanagementsystem.firebase.domain.FirebaseToken;
import com.example.studentmanagementsystem.firebase.repository.FirebaseTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FirebaseService {
    private final FirebaseTokenRepository firebaseTokenRepository;

    public FirebaseToken saveToken(FirebaseToken tokenEntity){
        return firebaseTokenRepository.saveToken(tokenEntity);
    }
}
