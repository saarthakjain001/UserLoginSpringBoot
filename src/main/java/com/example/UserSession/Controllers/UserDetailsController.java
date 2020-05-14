package com.example.UserSession.Controllers;

import java.util.Map;

import com.example.UserSession.Domain.Sessions;
import com.example.UserSession.Domain.UserDetails;
import com.example.UserSession.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDetailsController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/add")
    UserDetails addUser(@RequestBody UserDetails user) {
        return userService.AddUser(user);
    }

    @PutMapping("/user/update/{sessionId}")
    UserDetails updateContactDetails(@PathVariable Long sessionId, @RequestBody Map<String, Long> detail) {
        return userService.UpdateContact(sessionId, detail);
    }

    @GetMapping("/user/details/{sessionId}")
    UserDetails getDetails(@PathVariable Long sessionId) {
        return userService.ShowDetails(sessionId);
    }

}