package com.example.baduck.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    // PasswordEncoder => BCryptPasswordEncoder 사용
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // token 사용 방식 => csrf disable
                .csrf().disable()

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // Enable h2-console
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // 세션 사용 X => STATELESS 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeHttpRequests() // HttpServletRequest를 사용하는 요청들에 대한 접근제한 설정
                .requestMatchers(new AntPathRequestMatcher("/user/sign-up")).permitAll() // 회원가입 허용
                .requestMatchers(new AntPathRequestMatcher("/auth/**")).permitAll() // 인증, 인가 허용
                .requestMatchers(PathRequest.toH2Console()).permitAll()// h2-console 요청 인증 무시
                .anyRequest().authenticated() // 그 외 인증 없이 접근X

                .and()
                .apply(new JwtSecurityConfig(tokenProvider)); // JwtFilter를 addFilterBefore 로 등록했던 JwtSecurityConfig class 적용

        return httpSecurity.build();
    }
}
