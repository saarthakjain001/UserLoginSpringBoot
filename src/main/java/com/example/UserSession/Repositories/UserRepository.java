package com.example.UserSession.Repositories;

import com.example.UserSession.Domain.UserDetails;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findByUsernameAndPassword(String username, String password);

    UserDetails findByUsername(String username);
}