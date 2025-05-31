package com.example.studentmanagementsystem.library.controller;

import com.example.studentmanagementsystem.library.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LibraryController {
    private final BookService bookService;

    @PutMapping("/api/book/borrow")
    public void borrowBook(@RequestBody Map<String, String> requestMap){
        String title = requestMap.get("title");
    }
}
