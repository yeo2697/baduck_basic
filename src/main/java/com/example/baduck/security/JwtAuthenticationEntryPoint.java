package com.example.baduck.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

// 스프링 프레임워크에서 컴포넌트 스캔을 통해 빈으로 등록할 클래스를 나타내는 데 사용
// 스프링은 컴포넌트 스캔을 통해 프로젝트 내에서 @Component 어노테이션이 붙은 클래스들을 찾아서 자동으로 빈으로 등록한다.
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // 유효하지않은 자격증명 제공 및 접근시 401
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
