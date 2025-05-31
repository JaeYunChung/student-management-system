package com.example.studentmanagementsystem.library.repository;

import com.example.studentmanagementsystem.library.BookEntity;

public interface BookRepository {
    BookEntity findByTitle(String title);
    void updateBookSpare(BookEntity book);
    void deleteAll();
}
