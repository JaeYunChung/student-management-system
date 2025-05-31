package com.example.studentmanagementsystem.course.repository;

import com.example.studentmanagementsystem.member.TypeOfCourse;
import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.member.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaCourseRepository extends JpaRepository<CourseEntity, Long> {
}
