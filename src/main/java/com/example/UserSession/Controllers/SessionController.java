package com.example.UserSession.Controllers;

import java.util.Map;

import com.example.UserSession.Domain.Sessions;
import com.example.UserSession.Domain.SessionsDTO;
import com.example.UserSession.Domain.UserDetails;
import com.example.UserSession.Domain.UserDetailsDTO;
import com.example.UserSession.Repositories.SessionRepository;
import com.example.UserSession.Repositories.UserRepository;
import com.example.UserSession.Services.SessionService;
import com.example.UserSession.Services.SessionServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class SessionController {

    @Autowired
    private SessionServiceImpl sessionService;

    @PostMapping("/user/login")
    SessionsDTO createSession(@RequestBody Map<String, String> details) {
        return sessionToDto(sessionService.login(details));

    }

    @PutMapping("user/logout/{sessionId}")
    UserDetailsDTO exitSession(@PathVariable Long sessionId) {
        return userDetailsToDto(sessionService.logOut(sessionId));
    }

    private SessionsDTO sessionToDto(Sessions session) {
        SessionsDTO dto = new SessionsDTO();
        dto.setUsername(session.getUsername());
        dto.setSessionId(session.getId());
        return dto;
    }

    private UserDetailsDTO userDetailsToDto(UserDetails userDetails) {
        UserDetailsDTO dto = new UserDetailsDTO();
        dto.setUsername(userDetails.getUsername());
        dto.setName(userDetails.getName());
        dto.setMobileNumber(userDetails.getMobileNumber());
        return dto;
    }

}