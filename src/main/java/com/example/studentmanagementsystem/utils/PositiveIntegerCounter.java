package com.example.studentmanagementsystem.utils;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PositiveIntegerCounter {
    int count;
    public PositiveIntegerCounter(int count){
        this.count = count;
    }
    public void increase(){
        count++;
    }
    public void decrease(){
        if (count <= 0){
            throw new IllegalArgumentException("음수가 될 수 없습니다.");
        }
        count--;
    }
    public int getValue(){
        return count;
    }

    public boolean isZero(){
        return this.count == 0;
    }
}
