package com.example.studentmanagementsystem.course.dto;

public record CreateCourseDto(String name, String professorName, int courseSize,
                              int credit, String semester, String departmentName) {
}
