package com.example.studentmanagementsystem.security.repository;

import com.example.studentmanagementsystem.security.Member;
import com.example.studentmanagementsystem.security.MemberRoleMapping;
import com.example.studentmanagementsystem.security.Role;

import java.util.List;

public interface MemberRoleMappingRepository {
    List<Role> findByUsername(String username);
}