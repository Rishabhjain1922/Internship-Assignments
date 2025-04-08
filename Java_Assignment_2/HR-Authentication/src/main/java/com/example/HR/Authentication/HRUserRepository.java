package com.example.HR.Authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface HRUserRepository extends JpaRepository<HRUser, Long> {
    Optional<HRUser> findByUsername(String username);
    Optional<HRUser> findByUsernameAndPassword(String username, String password);
}