package com.example.baduck.dto.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NonNull
    @Size(min = 3, max = 20)
    private String userName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NonNull
    @Size(min = 3, max = 100)
    private String password;

    @NotNull
    private String userEmail;

    @NotNull
    private String userNickname;

    @NotNull
    private Character gender;

    @NotNull
    private String address;

    @NotNull
    private int birthYear;

    @NotNull
    private int birthMonth;

    @NotNull
    private int birthDay;
}
