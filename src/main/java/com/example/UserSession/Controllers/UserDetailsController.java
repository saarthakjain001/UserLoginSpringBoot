package com.example.UserSession.Controllers;

import java.util.Map;

import com.example.UserSession.Domain.Sessions;
import com.example.UserSession.Domain.SessionsDTO;
import com.example.UserSession.Domain.UserDetails;
import com.example.UserSession.Domain.UserDetailsDTO;
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
    UserDetailsDTO addUser(@RequestBody UserDetails user) {
        return userDetailsToDto(userService.AddUser(user));
    }

    @PutMapping("/user/update/{sessionId}")
    UserDetailsDTO updateContactDetails(@PathVariable Long sessionId, @RequestBody Map<String, Long> detail) {
        return userDetailsToDto(userService.UpdateContact(sessionId, detail));
    }

    @GetMapping("/user/details/{sessionId}")
    UserDetailsDTO getDetails(@PathVariable Long sessionId) {
        return userDetailsToDto(userService.ShowDetails(sessionId));
    }

    private UserDetailsDTO userDetailsToDto(UserDetails userDetails) {
        UserDetailsDTO dto = new UserDetailsDTO();
        dto.setUsername(userDetails.getUsername());
        dto.setName(userDetails.getName());
        dto.setMobileNumber(userDetails.getMobileNumber());
        return dto;
    }
}