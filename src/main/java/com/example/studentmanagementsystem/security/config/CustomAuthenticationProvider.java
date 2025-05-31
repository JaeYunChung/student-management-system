package com.example.studentmanagementsystem.security.config;

import com.example.studentmanagementsystem.security.Role;
import com.example.studentmanagementsystem.security.repository.MemberRoleMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final MemberRoleMappingRepository memberRoleMappingRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // AuthenticationManager가 authentication을 생성하여 AuthenticationProvider 구현체에 넘김
        // authentication은 사용자의 입력으로 받은 정보를 바탕으로 만든 userpasswordAuthenticationToken
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        List<Role> roles = memberRoleMappingRepository.findByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRole()));
        }
        if (userDetails != null && passwordEncoder.matches(password, userDetails.getPassword())){
            return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
            // UsernamePasswordAuthenticationToken의 3번째 파라미터에 authority에 관한 정보가 있으면 인증되었다고 판단한다.
        }
        throw new AuthenticationException("Invalid username or password") {};
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
