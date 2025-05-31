package com.example.studentmanagementsystem.security.repository;

import com.example.studentmanagementsystem.security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository{

    private final JpaRoleRepository jpaRoleRepository;
    @Override
    public Role findByRole(String role) {
        return jpaRoleRepository.findByRole(role);
    }
}
