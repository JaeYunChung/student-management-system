package com.example.studentmanagementsystem.library;


import com.example.studentmanagementsystem.member.domain.StudentEntity;
import com.example.studentmanagementsystem.security.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Entity
@NoArgsConstructor
@Getter
public class BookMemberMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public BookMemberMapping(BookEntity book, Member member){
        this.book = book;
        this.member = member;
        createdAt = Instant.now();
        limitAt = createdAt.plus(12, ChronoUnit.DAYS);
    }

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private Instant createdAt;
    private Instant limitAt;
}
