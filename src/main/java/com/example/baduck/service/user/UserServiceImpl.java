package com.example.baduck.service.user;

import com.example.baduck.dto.response.user.UserDetailResponse;
import com.example.baduck.dto.security.UserDto;
import com.example.baduck.entity.User;
import com.example.baduck.entity.UserAuthority;
import com.example.baduck.enums.AuthoritiesEnum;
import com.example.baduck.handler.exception.ErrorCode;
import com.example.baduck.handler.exception.custom.EmailDuplicateException;
import com.example.baduck.handler.exception.custom.EmailNotFoundException;
import com.example.baduck.repository.AuthorityRepository;
import com.example.baduck.repository.UserAuthorityRepository;
import com.example.baduck.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User signUp (UserDto userDto) {
        String userEmail = userDto.getUserEmail();;

        if (userRepository.findOneWithUserAuthoritiesByUserEmailAndDeletedDate(userEmail, null).isPresent()) {
            throw new EmailDuplicateException("이미 존재하는 E-Mail 입니다.", ErrorCode.EMAIL_DUPLICATION);
        }

        LocalDateTime currentTime = LocalDateTime.now();

        User user = User.builder()
                .userName(userDto.getUserName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .userEmail(userEmail)
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
                .authority(authorityRepository.findByAuthorityName(AuthoritiesEnum.ROLE_GENERAL.name()))
                .enabled(true)
                .createdDate(currentTime)
                .updatedDate(currentTime)
                .build();

        userAuthorityRepository.save(userAuthority);

        return signUpUser;
    }

    @Override
    public UserDetailResponse getUserDetails(String userEmail) {
        return new UserDetailResponse(userRepository.findOneWithUserAuthoritiesByUserEmailAndDeletedDate(userEmail, null).orElseThrow( () ->
                new EmailNotFoundException("해당 E-mail 이 존재하지 않습니다.", ErrorCode.EMAIL_NOT_FOUND)
        ));
    }
}
