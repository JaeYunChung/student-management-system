package com.example.studentmanagementsystem.library.repository;

import com.example.studentmanagementsystem.library.BookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository{
    private final JpaBookRepository jpaBookRepository;

    @Override
    public BookEntity findByTitle(String title) {
        return jpaBookRepository.findByTitle(title);
    }

    @Override
    public void updateBookSpare(BookEntity book) {
        jpaBookRepository.updateBookSpare(book);
    }

    @Override
    public void deleteAll() {
        jpaBookRepository.deleteAll();
    }
}
