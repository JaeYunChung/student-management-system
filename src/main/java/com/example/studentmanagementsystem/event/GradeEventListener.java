package com.example.studentmanagementsystem.event;

import com.example.studentmanagementsystem.course.entity.EvaluationEntity;
import com.example.studentmanagementsystem.grade.GradeEntity;
import com.example.studentmanagementsystem.grade.GradeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class GradeEventListener{

    private final GradeRepository gradeRepository;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleEvaluationCreated(EventCreateEntity entity) {

        EvaluationEntity evaluation = entity.getEntity();

        Optional<GradeEntity> optionalGradeEntity = gradeRepository.findByCourseAndStudent(
                evaluation.getCourse(),
                evaluation.getStudent()
        );

        double score = evaluation.getScore() * evaluation.getRatio() / 100.0;
        GradeEntity gradeEntity;

        if (optionalGradeEntity.isPresent()) {
            gradeEntity = optionalGradeEntity.get();
            gradeEntity.setGrade(gradeEntity.getGrade() + score);
        } else {
            gradeEntity = GradeEntity.builder()
                    .course(evaluation.getCourse())
                    .student(evaluation.getStudent())
                    .grade(score)
                    .build();
        }
        log.info("저장될 예정입니다.");
        gradeRepository.save(gradeEntity);
    }

//    @PreUpdate
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public void afterUpdateExamAndAssignmentScore(Object object){
//        EvaluationEntity evaluationEntity = (EvaluationEntity) object;
//        GradeRepository gradeRepository = BeanUtils.getBean(GradeRepository.class);
//        GradeEntity gradeEntity = gradeRepository.findByCourseAndStudent(evaluationEntity.getCourse(), evaluationEntity.getStudent())
//                .orElseThrow(() -> new IllegalArgumentException("수정할 점수가 없습니다."));
//        ExamRepository examRepository = BeanUtils.getBean(ExamRepository.class);
//        SubmissionRepository submissionRepository = BeanUtils.getBean(SubmissionRepository.class);
//        if (object instanceof ExamEntity) {
//            ExamEntity preExam = examRepository.findById(((ExamEntity) object).getId())
//                    .orElseThrow(()-> new IllegalArgumentException("수정할 점수가 없습니다."));
//            double grade = gradeEntity.getGrade() - preExam.getScore()*preExam.getRatio()/100f + evaluationEntity.getScore() * evaluationEntity.getRatio()/100f;
//            gradeEntity.setGrade(grade);
//            gradeRepository.save(gradeEntity);
//        }
//        else if(object instanceof SubmissionEntity){
//            SubmissionEntity preSubmit = submissionRepository.findById(((SubmissionEntity) object).getId())
//                    .orElseThrow(() -> new IllegalArgumentException("수정할 점수가 없습니다."));
//            double grade = gradeEntity.getGrade() - preSubmit.getScore()* preSubmit.getRatio()/100f + evaluationEntity.getScore()* evaluationEntity.getRatio()/100f;
//            gradeEntity.setGrade(grade);
//            gradeRepository.save(gradeEntity);
//
//        }
//
//    }
}
