package com.example.studentmanagementsystem.dormitory.service;

import com.example.studentmanagementsystem.dormitory.TypeOfDorm;
import com.example.studentmanagementsystem.dormitory.domain.DormitoryEntity;
import com.example.studentmanagementsystem.dormitory.dto.DormRegDto;
import com.example.studentmanagementsystem.dormitory.repository.DormitoryRepository;
import com.example.studentmanagementsystem.member.Degree;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import com.example.studentmanagementsystem.member.dto.CreateStudentDto;
import com.example.studentmanagementsystem.member.repository.StudentRepository;
import com.example.studentmanagementsystem.member.service.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DormitoryServiceTest {

    @Autowired
    DormitoryService dormitoryService;

    @Autowired
    DormitoryRepository dormitoryRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    StudentEntity student;


    @BeforeEach
    public void eachSetup(){
        LocalDateTime birthday = LocalDateTime.of(2003, 2, 6, 0, 0, 0);
        ZonedDateTime birthdayWithZone = birthday.atZone(ZoneId.systemDefault());
        CreateStudentDto studentDto = new CreateStudentDto("name", "010-2692-8532", "CyberSecurity"
        , birthdayWithZone.toInstant(), Degree.UNDERGRADUATE);
        student = studentService.createStudent(studentDto);
    }

    @AfterEach
    public void initialize(){
        studentRepository.deleteAll();
        dormitoryRepository.deleteAll();
    }


    @Test
    @Transactional
    void testRegisterDorm(){
        DormRegDto dto = new DormRegDto(student.getId(), TypeOfDorm.NAMJE);
        DormitoryEntity dormitory = dormitoryRepository.findByType(TypeOfDorm.NAMJE);
        Instant now = Instant.now();
        LocalTime time = now.atZone(ZoneId.systemDefault()).toLocalTime();
        if (now.isBefore(dormitory.getEndAt()) &&
        now.isAfter(dormitory.getStartAt()) &&
        time.isBefore(LocalTime.of(17, 0)) &&
        time.isAfter(LocalTime.of(10, 0))) {
            dormitoryService.registerDorm(dto);
            DormitoryEntity dormEntity = dormitoryRepository.findByType(TypeOfDorm.NAMJE);
            assertEquals(1, dormEntity.getNumOfApply());
        }
        else assertThrows(IllegalArgumentException.class, () -> dormitoryService.registerDorm(dto));
    }
}