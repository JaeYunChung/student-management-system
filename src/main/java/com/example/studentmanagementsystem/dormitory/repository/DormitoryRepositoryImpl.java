package com.example.studentmanagementsystem.dormitory.repository;

import com.example.studentmanagementsystem.dormitory.Dormitory;
import com.example.studentmanagementsystem.dormitory.TypeOfDorm;
import com.example.studentmanagementsystem.dormitory.domain.DormitoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class DormitoryRepositoryImpl implements DormitoryRepository{

    private final JpaDormitoryRepository jpaDormitoryRepository;

    @Override
    public DormitoryEntity save(Dormitory dormitory) {
        DormitoryEntity entity = jpaDormitoryRepository.save(dormitory.toEntity());
        return entity;
    }

    @Override
    public DormitoryEntity findByType(TypeOfDorm type) {
        return jpaDormitoryRepository.findByType(type);
    }

    @Override
    public void delete(Dormitory dormitory) {
        jpaDormitoryRepository.delete(dormitory.toEntity());
    }

    @Override
    public void deleteAll() {
        jpaDormitoryRepository.deleteAll();
    }

}
