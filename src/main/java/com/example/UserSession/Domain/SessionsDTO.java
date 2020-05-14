package com.example.UserSession.Domain;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class SessionsDTO {
    private String username;
    private Long sessionId;

    public SessionsDTO() {

    }

    SessionsDTO(String username, Long sessionId) {
        this.username = username;
        this.sessionId = sessionId;
    }
}