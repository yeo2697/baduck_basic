package com.example.baduck.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// 클래스의 final 필드나 @NotNull 어노테이션 필드를 이용하여 생성사를 자동 생성
@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final TokenProvider tokenProvider;

    @Override
    public void configure(HttpSecurity http) {

        // security 로직에 JwtFilter 등록
        http.addFilterBefore(
                new JwtFilter(tokenProvider),
                UsernamePasswordAuthenticationFilter.class
        );
    }
}
