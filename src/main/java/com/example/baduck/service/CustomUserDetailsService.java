package com.example.baduck.service;

import com.example.baduck.entity.User;
import com.example.baduck.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername (final String userEmail) {
        return userRepository.findOneWithUserAuthoritiesByUserEmailAndDeletedDate(userEmail, null)
                .map(user -> createUser(userEmail, user))
                .orElseThrow(() -> new UsernameNotFoundException(userEmail + " -> Database 에서 찾을 수 없습니다."));
    }

    private org.springframework.security.core.userdetails.User createUser (String userEmail, User user) {
        if (!user.getEnabled()) {
            throw new RuntimeException(userEmail + " -> 비활성화 상태");
        }

        List<GrantedAuthority> grantedAuthorities = user.getUserAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().getAuthorityName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUserEmail(),
                user.getPassword(),
                grantedAuthorities);
    }
}
