package com.example.studentmanagementsystem.dormitory.repository;

import com.example.studentmanagementsystem.dormitory.Dormitory;
import com.example.studentmanagementsystem.dormitory.TypeOfDorm;
import com.example.studentmanagementsystem.dormitory.domain.DormitoryEntity;

public interface DormitoryRepository {
    DormitoryEntity save(Dormitory dormitory);
    DormitoryEntity findByType(TypeOfDorm type);
    void delete(Dormitory dormitory);
    void deleteAll();
}
