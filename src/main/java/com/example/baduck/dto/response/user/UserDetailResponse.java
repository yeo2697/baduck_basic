package com.example.baduck.dto.response.user;

import com.example.baduck.dto.response.userAuthority.UserAuthorityResponse;
import com.example.baduck.entity.User;
import com.example.baduck.entity.UserAuthority;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailResponse {
    private Long id;
    private String userName;
    private String userNickname;
    private String userEmail;
    private Character gender;
    private int birthYear;
    private int birthMonth;
    private int birthDay;
    private String address;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Set<UserAuthorityResponse> userAuthorities;

    public UserDetailResponse(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.userNickname = user.getUserNickname();
        this.userEmail = user.getUserEmail();
        this.gender = user.getGender();
        this.birthYear = user.getBirthYear();
        this.birthMonth = user.getBirthMonth();
        this.birthDay = user.getBirthDay();
        this.address = user.getAddress();
        this.createdDate = user.getCreatedDate();
        this.updatedDate = user.getUpdatedDate();
        this.userAuthorities = user.getUserAuthorities().stream().map(UserAuthorityResponse::new).collect(Collectors.toSet());
    }
}
