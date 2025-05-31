package com.example.studentmanagementsystem.dormitory.service;

import com.example.studentmanagementsystem.dormitory.Dormitory;
import com.example.studentmanagementsystem.dormitory.domain.DormitoryEntity;
import com.example.studentmanagementsystem.dormitory.dto.DormRegDto;
import com.example.studentmanagementsystem.dormitory.repository.DormitoryRepository;
import com.example.studentmanagementsystem.member.Student;
import com.example.studentmanagementsystem.member.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class DormitoryService {
    private final DormitoryRepository dormitoryRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public DormitoryEntity createDormitory(Dormitory dormitory){
        return dormitoryRepository.save(dormitory);
    }
    @Transactional
    public void removeDormitory(Dormitory dormitory){
        dormitoryRepository.delete(dormitory);
    }
    @Transactional
    public void registerDorm(DormRegDto regDto){
        Student student = studentRepository.findById(regDto.studentId()).toStudent();
        DormitoryEntity dormitoryEntity = dormitoryRepository.findByType(regDto.type());
        Dormitory dormitory = dormitoryEntity.toDormitory();

        dormitory.register(Instant.now());
        student.registerDormitory(dormitory);

        dormitoryRepository.save(dormitory);
        studentRepository.save(student);
    }
}
