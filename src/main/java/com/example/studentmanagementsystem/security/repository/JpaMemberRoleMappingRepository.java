package com.example.studentmanagementsystem.security.repository;

import com.example.studentmanagementsystem.security.MemberRoleMapping;
import com.example.studentmanagementsystem.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaMemberRoleMappingRepository extends JpaRepository<MemberRoleMapping, Long> {
    @Modifying
    @Query("select r from MemberRoleMapping mr join mr.member m join mr.role r where m.username=:username")
    List<Role> findByUsername(String username);
}
