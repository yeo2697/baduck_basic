package com.example.baduck.repository;

import com.example.baduck.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneWithUserAuthoritiesByUserEmailAndDeletedDate(String userEmail, LocalDateTime deletedDate);
    Page <User> findAll(Pageable pageable);
}