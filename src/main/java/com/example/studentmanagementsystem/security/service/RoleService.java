package com.example.studentmanagementsystem.security.service;

import com.example.studentmanagementsystem.security.Role;
import com.example.studentmanagementsystem.security.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public Role findByRole(String role){
        return roleRepository.findByRole(role);
    }
}
