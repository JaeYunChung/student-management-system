package com.example.studentmanagementsystem.dormitory;

import com.example.studentmanagementsystem.dormitory.domain.DormitoryEntity;
import com.example.studentmanagementsystem.utils.PositiveIntegerCounter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Getter
@Builder
@AllArgsConstructor
public class Dormitory {
    private Long id;
    private final int totalSize;
    private PositiveIntegerCounter spareSize;
    private PositiveIntegerCounter numOfApply;
    private int cost;
    private TypeOfDorm type;
    private Instant startAt;
    private Instant endAt;

    public Dormitory(int cost, TypeOfDorm type, int totalSize, Instant startAt){
        this.cost = cost;
        this.type = type;
        this.totalSize = totalSize;
        this.spareSize = new PositiveIntegerCounter(totalSize);
        this.numOfApply = new PositiveIntegerCounter();
        this.startAt = startAt;
        this.endAt = startAt
                .plus(5, ChronoUnit.DAYS)
                .plus(6, ChronoUnit.HOURS);
    }

    public void register(Instant applyDay){
        if (applyDay.isAfter(endAt) || applyDay.isBefore(startAt)){
            throw new IllegalArgumentException("신청 날짜가 아닙니다.");
        }
        LocalTime now = applyDay.atZone(ZoneId.systemDefault()).toLocalTime();
        if (now.isBefore(LocalTime.of(10, 0)) || now.isAfter(LocalTime.of(17, 0))){
            throw new IllegalArgumentException("신청시간이 아닙니다.");
        }
        if (spareSize.isZero()){
            throw new IllegalArgumentException("기숙사 정원이 모두 찼습니다.");
        }
        this.spareSize.decrease();
        this.numOfApply.increase();
    }

    public DormitoryEntity toEntity(){
        return DormitoryEntity.builder()
                .id(id)
                .cost(cost)
                .type(type)
                .totalSize(totalSize)
                .spareSize(spareSize.getValue())
                .numOfApply(numOfApply.getValue())
                .startAt(startAt)
                .endAt(endAt)
                .build();
    }

}
