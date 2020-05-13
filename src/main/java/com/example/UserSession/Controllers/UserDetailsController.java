package com.example.UserSession.Controllers;

import java.util.Map;

import com.example.UserSession.Domain.Sessions;
import com.example.UserSession.Domain.UserDetails;
import com.example.UserSession.Repositories.SessionRepository;
import com.example.UserSession.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDetailsController {

    private final UserRepository repository;
    private final SessionRepository sesrepository;

    UserDetailsController(UserRepository repository, SessionRepository sesrepository) {
        this.repository = repository;
        this.sesrepository = sesrepository;
    }

    @PostMapping("/user/add")
    UserDetails addUser(@RequestBody UserDetails user) {
        return repository.save(user);
    }

    @PutMapping("/user/update/{sessionId}")
    UserDetails updateContactDetails(@PathVariable Long sessionId, @RequestBody Map<String, Long> detail) {
        Sessions session = sesrepository.getOne(sessionId);
        if (session == null)
            return null;
        String username = session.getUsername();
        UserDetails user = repository.findByUsername(username);
        user.setMobileNumber(detail.get("contact"));
        repository.save(user);
        return user;
    }

    @GetMapping("/user/details/{sessionId}")
    UserDetails getDetails(@PathVariable Long sessionId) {
        Sessions session = sesrepository.getOne(sessionId);
        if (session == null)
            return null;
        String username = session.getUsername();
        UserDetails user = repository.findByUsername(username);
        return user;
    }

}