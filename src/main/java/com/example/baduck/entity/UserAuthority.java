package com.example.baduck.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_authority")
public class UserAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_id", nullable = false)
    private Authority authority;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedDate;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;
}
