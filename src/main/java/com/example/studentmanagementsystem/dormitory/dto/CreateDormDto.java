package com.example.studentmanagementsystem.dormitory.dto;

import com.example.studentmanagementsystem.dormitory.TypeOfDorm;

import java.time.Instant;

public record CreateDormDto(int cost, TypeOfDorm type, int size, Instant startAt) {
}
