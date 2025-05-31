package com.example.studentmanagementsystem.security.repository;

import com.example.studentmanagementsystem.security.Member;
import com.example.studentmanagementsystem.security.MemberRoleMapping;
import com.example.studentmanagementsystem.security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRoleMappingRepositoryImpl implements MemberRoleMappingRepository{
    private final JpaMemberRoleMappingRepository jpaMemberRoleMappingRepository;
    @Override
    public List<Role> findByUsername(String username) {
        return jpaMemberRoleMappingRepository.findByUsername(username);
    }
}
