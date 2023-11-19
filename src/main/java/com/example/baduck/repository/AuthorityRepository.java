package com.example.baduck.repository;

import com.example.baduck.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByAuthorityName (String findBtAuthorityName);
}