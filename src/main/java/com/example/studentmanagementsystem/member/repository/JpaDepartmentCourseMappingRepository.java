package com.example.studentmanagementsystem.member.repository;

import com.example.studentmanagementsystem.member.domain.Department;
import com.example.studentmanagementsystem.member.domain.DepartmentCourseMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaDepartmentCourseMappingRepository extends JpaRepository<DepartmentCourseMapping, Long> {
    @Modifying
    @Query("select m.courseName from DepartmentCourseMapping m where m.department = :department and m.enterYear = :enterYear")
    List<String> findCourseNamesByDepartmentAndEnterYear(Department department, int enterYear);

    List<DepartmentCourseMapping> findAllByDepartmentAndEnterYear(Department department, int enterYear);
}
