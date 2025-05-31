package com.example.studentmanagementsystem.course.repository;

import com.example.studentmanagementsystem.member.TypeOfCourse;
import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.member.domain.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CourseRepositoryImpl implements  CourseRepository{
    private final JpaCourseRepository jpaCourseRepository;

    @Override
    public CourseEntity save(CourseEntity course) {
        return jpaCourseRepository.save(course);
    }

    @Override
    public CourseEntity findById(Long id) {
        return jpaCourseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 강의가 없습니다."));
    }

    @Override
    public List<CourseEntity> findAll() {
        return jpaCourseRepository.findAll();
    }

    @Override
    public void deleteAll() {
        jpaCourseRepository.deleteAll();
    }

    @Override
    public void deleteById(Long id) {
        jpaCourseRepository.deleteById(id);
    }
}
