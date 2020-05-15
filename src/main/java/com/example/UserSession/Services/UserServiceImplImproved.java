package com.example.UserSession.Services;

import java.util.Map;

import com.example.UserSession.Domain.UserDetails;
import com.example.UserSession.Repositories.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImplImproved implements UserServiceImproved {

    private final UserRepository repository;

    UserServiceImplImproved(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails AddUser(UserDetails user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserDetails ShowDetails(Long sessionId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserDetails UpdateContact(Long sessionId, Map<String, Long> detail) {
        // TODO Auto-generated method stub
        return null;
    }

}