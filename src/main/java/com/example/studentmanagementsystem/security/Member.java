package com.example.studentmanagementsystem.security;

import com.example.studentmanagementsystem.member.domain.StudentEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<MemberRoleMapping> mapping;

    public Member(String username, String password){
        this.username = username;
        this.password = password;
//        this.mapping = new ArrayList<>();
    }

    @OneToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return mapping.stream()
//                .map(each -> each.getRole().getRole())
//                .map(StringBuilder::new)
//                .map(each -> each.insert(0, "Role_"))
//                .map(each -> new SimpleGrantedAuthority(each.toString()))
//                .toList();
        return List.of();
    }

//    public void appendRole(MemberRoleMapping mapping){
//        this.mapping.add(mapping);
//    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
