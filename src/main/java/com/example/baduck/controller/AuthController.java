package com.example.baduck.controller;

import com.example.baduck.dto.security.SignInDto;
import com.example.baduck.dto.security.TokenDto;
import com.example.baduck.dto.security.UserDto;
import com.example.baduck.security.JwtFilter;
import com.example.baduck.security.TokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    @PostMapping("/authenticate")
    public ResponseEntity <TokenDto> signIn (@Valid @RequestBody SignInDto signInDto) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken (signInDto.getUserEmail(), signInDto.getPassword());

            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = tokenProvider.createToken(authentication);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

            return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error => " + e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
