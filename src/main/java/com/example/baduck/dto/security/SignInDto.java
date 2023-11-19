package com.example.baduck.dto.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInDto {
    @NotNull
    @Email
    private String userEmail;

    @NotNull
    @Size(min = 6, max = 20)
    private String password;
}
