package com.example.UserSession.Services;

import java.util.Map;

import com.example.UserSession.Domain.Sessions;
import com.example.UserSession.Domain.UserDetails;

public interface SessionService {
    Sessions login(Map<String, String> details);

    UserDetails logOut(Long sessionId);
}