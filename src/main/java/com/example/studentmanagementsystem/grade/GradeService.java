package com.example.studentmanagementsystem.grade;

import com.example.studentmanagementsystem.course.Course;
import com.example.studentmanagementsystem.member.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;
    public List<GradeEntity> getGrades(Student student){
        return gradeRepository.findAllByStudent(student.toEntity());
    }
    // 학생 정보를 입력하지 않고도 강의 정보만으로 전체 학생의 점수를 업데이트 할 수 있을 것이다.
//    public void createGrades(Student student, Course course)
//    {
//        List<SubmissionEntity> submissions = submissionRepository.findByCourseAndStudent(course.toEntity(), student.toEntity());
//        List<ExamEntity> exams = examRepository.findByCourseAndStudent(course.toEntity(), student.toEntity());
//        Double submitGradeValue = submissions.stream()
//                .mapToDouble(each -> (each.getGrade() *each.getRatio()) / 100.0)
//                .sum();
//        Double examGradeValue = exams.stream()
//                .mapToDouble(each -> (each.getGrade() * each.getRatio()) / 100)
//                .sum();
//        Double gradeValue = submitGradeValue + examGradeValue;
//        Grade grade = Grade.builder()
//                .student(student)
//                .course(course)
//                .grade(gradeValue)
//                .build();
//        gradeRepository.save(grade.toEntity());
//    }


    public void createRelativeGrade(Course course){
        List<GradeEntity> grades = gradeRepository.findAllByCourse(course.toEntity());
        int gradeSize = grades.size();
        List<GradeEntity> sortedGrades = grades.stream()
                .sorted((o1, o2) -> {
                    if (o1.getGrade() - o2.getGrade() < 0) return 1;
                    else if (o1.getGrade() - o2.getGrade() > 0) return -1;
                    else return 0;
                })
                .toList();
        for (int i = 0 ; i < gradeSize; i++)
        {
            GradeEntity grade = grades.get(i);
            if (grade.getGrade() == 0) continue;
            float ratio =  i / (float) gradeSize;

            if (ratio < 0.1) grade.setGrade(4.0);
            else if (ratio < 0.5) grade.setGrade(3.0);
            else if (ratio < 0.9) grade.setGrade(2.0);
            else grade.setGrade(0.0);
        }
        gradeRepository.saveAll(grades);
    }
    public void adjustFinalScore(Course course, Student student)
    {
        GradeEntity grade = gradeRepository.findByCourseAndStudent(course.toEntity(), student.toEntity())
                .orElseThrow(() -> new IllegalArgumentException("해당 학생의 점수가 없습니다."));
        double adjustedGrade = grade.getGrade() + 0.5;
        grade.setGrade(adjustedGrade);
        gradeRepository.save(grade);
    }
}
