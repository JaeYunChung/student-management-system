package com.example.studentmanagementsystem.member.repository;

import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import com.example.studentmanagementsystem.security.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaStudentRepository extends JpaRepository<StudentEntity, Long> {
    StudentEntity save(StudentEntity student);
    @Modifying
    @Query(value = "select m.student from StudentCourseMapping m where m.course = :course")
    List<StudentEntity> findAllByCourse(CourseEntity course);

    StudentEntity findByMember(Member member);
}
