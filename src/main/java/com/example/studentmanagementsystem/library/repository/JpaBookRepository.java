package com.example.studentmanagementsystem.library.repository;

import com.example.studentmanagementsystem.library.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaBookRepository extends JpaRepository<BookEntity, Long> {
    BookEntity findByTitle(String title);

    @Modifying
    @Query(value = "update BookEntity b set b.spare = b.spare - 1 where b = :book")
    void updateBookSpare(BookEntity book);
}
