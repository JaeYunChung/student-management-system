package com.example.studentmanagementsystem.graduation;

import com.example.studentmanagementsystem.course.Course;
import com.example.studentmanagementsystem.graduation.repository.EnglishExamRepository;
import com.example.studentmanagementsystem.member.TypeOfCourse;
import com.example.studentmanagementsystem.member.Degree;
import com.example.studentmanagementsystem.member.Student;
import com.example.studentmanagementsystem.member.domain.Department;
import com.example.studentmanagementsystem.member.domain.DepartmentEnglishExamMapping;
import com.example.studentmanagementsystem.member.repository.DepartmentCourseMappingRepository;
import com.example.studentmanagementsystem.member.repository.DepartmentEnglishExamMappingRepository;
import com.example.studentmanagementsystem.member.service.DepartmentService;
import com.example.studentmanagementsystem.member.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class GraduateService {
    private final StudentService studentService;
    private final DepartmentService departmentService;
    private final EnglishExamRepository englishExamRepository;
    private final DepartmentEnglishExamMappingRepository departmentEnglishExamMappingRepository;
    // 졸업요건
    @Transactional
    public boolean canGraduate(Student student){
        Department department = student.getDepartment();
        int enterYear = ZonedDateTime.ofInstant(student.getEnterAt(), ZoneId.systemDefault()).getYear();

        if (student.getTotalGrade() < department.getMinGrade()){
            log.info("최소 점수를 만족시키지 않습니다.");
            return false;
        }

        if (student.getTotalCredit() < department.getMinCredit())
        {
            log.info("최소학점을 만족시키지 않습니다.");
            return false;
        }

        // 재수강해야하는 과목은 제외한다.
        List<Course> completedCourses = studentService.getAdmittedCourses(student);
        Set<String> requiredCourseNames = departmentService.getRequiredCourses(department, enterYear);
        List<String> completedRequiredCourseNames = getCompletedRequiredCourseNames(completedCourses, requiredCourseNames);

        // 필수 수강과목을 모두 수강하지 않는다면 졸업할 수 없다.
        if (requiredCourseNames.size() != completedRequiredCourseNames.size())
        {
            log.info("필수수강과목을 모두 수강하지 않았습니다.");
            return false;
        }
        Set<String> optionRequiredCourseNames = departmentService.getOptionRequiredCourses(department, enterYear);
        int optionRequiredCoursesMinCredit = getOptionRequiredCoursesCredits(completedCourses, optionRequiredCourseNames);
        // 전공선택을 일정 학점 이상 수강하지 않는다면 졸업할 수 없다.
        if (optionRequiredCoursesMinCredit < department.getOptionRequiredCourseMinCredit())
        {
            log.info("전공과목을 더 들으셔야 합니다.");
            return false;
        }

        List<DepartmentEnglishExamMapping> englishExamMappings = departmentEnglishExamMappingRepository.findAllByDepartment(department);
        Map<EnglishExam, Integer> englishExamTypeAndScore = englishEntitiesToMap(englishExamMappings);
        List<EnglishExamEntity> englishExamEntities = englishExamRepository.findAllByStudent(student.toEntity());
        for (EnglishExamEntity entity  : englishExamEntities)
        {
            if (englishExamTypeAndScore.get(entity.getType()) <= entity.getScore())
            {
                return true;
            }
        }
        log.info("공인 영어 성적 점수가 기준 미달입니다.");
        return false;
    }
    public List<String> getCompletedRequiredCourseNames(List<Course> completedCourses, Set<String> requiredCourseNames){
        return completedCourses
                .stream()
                .map(Course::getName)
                .filter(requiredCourseNames::contains)
                .toList();
    }
    public int getOptionRequiredCoursesCredits(List<Course> completedCourses, Set<String> optionRequiredCourseNames){
        return completedCourses
                .stream()
                .filter(each -> optionRequiredCourseNames.contains(each.getName()))
                .mapToInt(Course::getCredit)
                .sum();
    }

    public Map<EnglishExam, Integer> englishEntitiesToMap(List<DepartmentEnglishExamMapping> englishExamMappings){
        Map<EnglishExam, Integer> map = new HashMap<>();
        for (DepartmentEnglishExamMapping entity : englishExamMappings){
            map.put(entity.getType(), entity.getScore());
        }
        return map;
    }

    public void graduateStudent(Student student)
    {
        if (canGraduate(student))
        {
            Degree degree = student.getDegree();
            switch(degree)
            {
                case UNDERGRADUATE -> degree = Degree.BACHELOR;
                case COURSE_OF_MASTER -> degree = Degree.MASTER;
                case COURSE_OF_DOCTOR -> degree = Degree.PHD;
            }
            studentService.saveStudent(student);
        }
    }
}
