package com.example.studentmanagementsystem.dormitory.controller;

import com.example.studentmanagementsystem.dormitory.Dormitory;
import com.example.studentmanagementsystem.dormitory.domain.DormitoryEntity;
import com.example.studentmanagementsystem.dormitory.dto.CreateDormDto;
import com.example.studentmanagementsystem.dormitory.dto.DormRegDto;
import com.example.studentmanagementsystem.dormitory.service.DormitoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class DormitoryController {

    private final DormitoryService dormitoryService;

    // 기숙사 신청
    @PostMapping("/dormitory/reg")
    public ResponseEntity<?> registrationDormitory(DormRegDto dto){
        dormitoryService.registerDorm(dto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
    // 기숙사 생성
    @PostMapping("/dormitory/create")
    public ResponseEntity<?> createDormitory(CreateDormDto dto){
        Dormitory dormitory = new Dormitory(dto.cost(), dto.type(), dto.size(), dto.startAt());
        DormitoryEntity dormitoryEntity = dormitoryService.createDormitory(dormitory);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dormitoryEntity);
    }
}
