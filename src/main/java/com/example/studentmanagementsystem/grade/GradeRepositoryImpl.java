package com.example.studentmanagementsystem.grade;

import com.example.studentmanagementsystem.course.entity.CourseEntity;
import com.example.studentmanagementsystem.member.domain.StudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GradeRepositoryImpl implements GradeRepository {

    private final JpaGradeRepository jpaGradeRepository;

    @Override
    public void save(GradeEntity grade) {
        jpaGradeRepository.save(grade);
    }

    @Override
    public void saveAll(List<GradeEntity> grades) {
        jpaGradeRepository.saveAll(grades);
    }

    @Override
    public List<GradeEntity> findAllByStudent(StudentEntity student)
    {
        return jpaGradeRepository.findAllByStudent(student);
    }

    @Override
    public List<GradeEntity> findAllByCourse(CourseEntity course) {
        return jpaGradeRepository.findAllByCourse(course);
    }

    @Override
    public Optional<GradeEntity> findByCourseAndStudent(CourseEntity course, StudentEntity student) {
        return jpaGradeRepository.findByCourseAndStudent(course, student);
    }

    @Override
    public void deleteAll() {
        jpaGradeRepository.deleteAll();;
    }

    @Override
    public void deleteById(Long id) {
        jpaGradeRepository.deleteById(id);
    }

    @Override
    public List<GradeEntity> findAll() {
        return jpaGradeRepository.findAll();
    }
}
