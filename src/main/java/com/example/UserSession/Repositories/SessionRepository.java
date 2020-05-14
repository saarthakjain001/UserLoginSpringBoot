package com.example.UserSession.Repositories;

import com.example.UserSession.Domain.Sessions;
import com.example.UserSession.Domain.UserDetails;
import com.example.UserSession.Domain.Sessions.statusValues;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Sessions, Long> {

    Sessions findByUsername(String username);

    Sessions findByStatus(statusValues status);

    Sessions findByUsernameAndStatus(String username, statusValues status);
}