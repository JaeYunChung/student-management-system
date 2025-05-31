package com.example.studentmanagementsystem.dormitory.domain;

import com.example.studentmanagementsystem.dormitory.Dormitory;
import com.example.studentmanagementsystem.dormitory.TypeOfDorm;
import com.example.studentmanagementsystem.utils.PositiveIntegerCounter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DormitoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cost;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private TypeOfDorm type;

    private int totalSize;
    private int spareSize;
    private int numOfApply;
    private Instant startAt;
    private Instant endAt;

    public DormitoryEntity(Dormitory dormitory)
    {
        this.cost = dormitory.getCost();
        this.type = dormitory.getType();
        this.totalSize = dormitory.getTotalSize();
        this.spareSize = dormitory.getSpareSize().getValue();
        this.numOfApply = dormitory.getNumOfApply().getValue();
        this.startAt = dormitory.getStartAt();
        this.endAt = dormitory.getEndAt();
    }

    public Dormitory toDormitory(){
        return Dormitory.builder()
                .id(id)
                .cost(this.cost)
                .type(this.type)
                .totalSize(this.totalSize)
                .spareSize(new PositiveIntegerCounter(this.spareSize))
                .numOfApply(new PositiveIntegerCounter(this.numOfApply))
                .startAt(this.startAt)
                .endAt(this.endAt)
                .build();
    }
}
