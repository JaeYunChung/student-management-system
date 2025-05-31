package com.example.studentmanagementsystem.dormitory.repository;


import com.example.studentmanagementsystem.dormitory.TypeOfDorm;
import com.example.studentmanagementsystem.dormitory.domain.DormitoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDormitoryRepository extends JpaRepository<DormitoryEntity, Long> {
    DormitoryEntity findByType(TypeOfDorm type);
}
