package com.example.baduck.service;

import com.example.baduck.dto.security.UserDto;
import com.example.baduck.entity.User;
import com.example.baduck.entity.UserAuthority;
import com.example.baduck.enums.AuthoritiesEnum;
import com.example.baduck.repository.AuthorityRepository;
import com.example.baduck.repository.UserAuthorityRepository;
import com.example.baduck.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User signUp (UserDto userDto) {
        if (userRepository.findOneWithUserAuthoritiesByUserEmailAndDeletedDate(userDto.getUserEmail(), null).orElse(null) != null) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        LocalDateTime currentTime = LocalDateTime.now();

        User user = User.builder()
                .userName(userDto.getUserName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .userEmail(userDto.getUserEmail())
                .userNickname(userDto.getUserNickname())
                .gender(userDto.getGender())
                .address(userDto.getAddress())
                .birthYear(userDto.getBirthYear())
                .birthMonth(userDto.getBirthMonth())
                .birthDay(userDto.getBirthDay())
                .enabled(true)
                .createdDate(currentTime)
                .updatedDate(currentTime)
                .build();

        User signUpUser = userRepository.save(user);

        UserAuthority userAuthority = UserAuthority.builder()
                .user(signUpUser)
                .authority(authorityRepository.findByAuthorityName(AuthoritiesEnum.GENERAL.getAuthorityName()))
                .enabled(true)
                .createdDate(currentTime)
                .updatedDate(currentTime)
                .build();

        userAuthorityRepository.save(userAuthority);

        return signUpUser;
    }
}
