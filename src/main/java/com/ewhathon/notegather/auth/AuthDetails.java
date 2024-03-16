package com.ewhathon.notegather.auth;


import com.ewhathon.notegather.domain.entity.Student;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
@RequiredArgsConstructor
public class AuthDetails implements UserDetails {
    /* 인증된 사용자에 대한 세부 정보를 다루는 UserDetails의 구현체 */

    private final Student student;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /* 맴버의 권한 반환 */
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(student.getUserRole().name()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return student.getPassword();
    }

    @Override
    public String getUsername() {
        /* 사용자의 식별자(일반적으로 유저명이나 이메일) */
        return student.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
