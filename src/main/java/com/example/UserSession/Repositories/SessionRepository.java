package com.example.UserSession.Repositories;

import com.example.UserSession.Domain.Sessions;
import com.example.UserSession.Domain.UserDetails;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Sessions, Long> {

    Sessions findByUsername(String username);
}