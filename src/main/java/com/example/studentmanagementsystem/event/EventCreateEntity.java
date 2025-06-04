package com.example.studentmanagementsystem.event;

import com.example.studentmanagementsystem.course.entity.EvaluationEntity;


public class EventCreateEntity {
    private final EvaluationEntity entity;

    public EventCreateEntity(EvaluationEntity entity) {
        this.entity = entity;
    }

    public EvaluationEntity getEntity() {
        return entity;
    }
}

