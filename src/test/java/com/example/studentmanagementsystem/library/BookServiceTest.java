package com.example.studentmanagementsystem.library;

import com.example.studentmanagementsystem.library.repository.BookRepository;
import com.example.studentmanagementsystem.library.repository.BookStudentMappingRepository;
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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookStudentMappingRepository bookStudentMappingRepository;

    @Autowired
    BookService bookService;

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
        bookStudentMappingRepository.deleteAll();
        studentRepository.deleteAll();
    }

//    @Test
//    void testUpdateSpareAfterBorrowBook(){
//        BookMemberMapping bookMemberMapping = bookService.borrowBook("컴퓨터구조");
//        BookEntity book = bookRepository.findByTitle("컴퓨터구조");
//        assertEquals(2, book.getSpare());
//    }
}