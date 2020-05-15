package com.example.UserSession.Services;

import java.util.Map;

import com.example.UserSession.Domain.Sessions;
import com.example.UserSession.Domain.UserDetails;

public interface SessionServiceImproved {
    Sessions generateSession(Map<String, String> details);

    Sessions endSession(Long sessionId);

    Sessions validateSession(Long sessionId)
}