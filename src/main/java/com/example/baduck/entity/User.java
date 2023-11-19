package com.example.baduck.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "user_nickname", nullable = false, unique = true)
    private String userNickname;

    @Column(name = "user_email", unique = true, nullable = false)
    @Email
    private String userEmail;

    @Column(nullable = false)
    private Character gender;

    @JsonIgnore
    @Column(length = 100, nullable = false)
    private String password;

    @Column(name = "birth_year", nullable = false)
    private int birthYear;

    @Column(name = "birth_month", nullable = false)
    private int birthMonth;

    @Column(name = "birth_day", nullable = false)
    private int birthDay;

    @Column(name = "address", nullable = false)
    private String address;

    @JsonIgnore
    @Column(nullable = false)
    private Boolean enabled;

    // 서비스 등록일
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedDate;

    @JsonIgnore
    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    /**
     * 이하 Associate
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Set<UserAuthority> userAuthorities;
}
