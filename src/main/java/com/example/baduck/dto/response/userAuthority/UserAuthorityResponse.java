package com.example.baduck.dto.response.userAuthority;

import com.example.baduck.dto.response.authority.AuthorityResponse;
import com.example.baduck.entity.UserAuthority;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthorityResponse {
    private Long id;
    private AuthorityResponse authority;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public UserAuthorityResponse(UserAuthority userAuthority) {
        this.id = userAuthority.getId();
        this.authority = new AuthorityResponse(userAuthority.getAuthority());
        this.createdDate = userAuthority.getCreatedDate();
        this.updatedDate = userAuthority.getUpdatedDate();
    }
}
