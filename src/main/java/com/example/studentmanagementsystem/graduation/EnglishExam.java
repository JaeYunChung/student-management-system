package com.example.studentmanagementsystem.graduation;

public enum EnglishExam {

    TOEIC,
    OPIC,
    TOEFL,
    IBT;

    public static EnglishExamEntity toEntity(EnglishExam type, int score){
        return EnglishExamEntity.builder()
                .type(type)
                .score(score)
                .build();
    }
}
