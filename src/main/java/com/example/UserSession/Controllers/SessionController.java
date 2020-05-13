package com.example.UserSession.Controllers;

import java.util.Map;

import com.example.UserSession.Domain.Sessions;
import com.example.UserSession.Domain.UserDetails;
import com.example.UserSession.Repositories.SessionRepository;
import com.example.UserSession.Repositories.UserRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class SessionController {

    private final SessionRepository sesrepository;
    private final UserRepository userrepository;

    SessionController(SessionRepository repository, UserRepository userrepository) {
        this.sesrepository = repository;
        this.userrepository = userrepository;
    }

    @PostMapping("/user/login")
    Sessions createSession(@RequestBody Map<String, String> details) {
        UserDetails user = userrepository.findByUsernameAndPassword(details.get("username"), details.get("password"));
        if (user == null) {
            return null;
        } else {
            if (sesrepository.findByUsername(details.get("username")) == null) {
                Random rand = new Random();

                Sessions newSession = new Sessions(details.get("username"), rand.nextLong());
                sesrepository.save(newSession);
                return newSession;
            }
            return sesrepository.findByUsername(details.get("username"));
        }

    }

    @DeleteMapping("user/logout/{sessionId}")
    UserDetails exitSession(@PathVariable Long sessionId) {
        Sessions session = sesrepository.getOne(sessionId);
        if (session == null)
            return null;
        sesrepository.deleteById(sessionId);
        String username = session.getUsername();
        UserDetails user = userrepository.findByUsername(username);
        return user;
    }
}