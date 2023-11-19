package com.example.baduck.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "authority_name", nullable = false, unique = true)
    private String authorityName; // 관리자, 일반, 성인 등

    @JsonIgnore
    @Column(nullable = false)
    private Boolean enabled;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_id")
    private Set<UserAuthority> userAuthorities;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedDate;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;
}
