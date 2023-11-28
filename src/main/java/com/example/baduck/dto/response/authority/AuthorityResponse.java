package com.example.baduck.dto.response.authority;

import com.example.baduck.entity.Authority;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityResponse {
    private Long id;
    private String authorityName; // 관리자, 일반, 성인 등
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    public AuthorityResponse(Authority authority) {
        this.id = authority.getId();
        this.authorityName = authority.getAuthorityName();
        this.createdDate = authority.getCreatedDate();
        this.updatedDate = authority.getUpdatedDate();
    }
}
