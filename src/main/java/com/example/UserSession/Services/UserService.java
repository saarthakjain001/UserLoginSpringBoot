package com.example.UserSession.Services;

import java.util.Map;

import com.example.UserSession.Domain.UserDetails;

public interface UserService {
    UserDetails AddUser(UserDetails user);

    UserDetails ShowDetails(Long sessionId);

    UserDetails UpdateContact(Long sessionId, Map<String, Long> detail);
}