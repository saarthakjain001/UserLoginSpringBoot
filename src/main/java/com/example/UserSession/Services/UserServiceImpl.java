package com.example.UserSession.Services;

import java.util.Map;

import com.example.UserSession.Domain.Sessions;
import com.example.UserSession.Domain.UserDetails;
import com.example.UserSession.Domain.Sessions.statusValues;
import com.example.UserSession.Repositories.SessionRepository;
import com.example.UserSession.Repositories.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final SessionRepository sesrepository;

    UserServiceImpl(UserRepository repository, SessionRepository sesrepository) {
        this.repository = repository;
        this.sesrepository = sesrepository;
    }

    @Override
    public UserDetails AddUser(UserDetails user) {
        return repository.save(user);
    }

    @Override
    public UserDetails ShowDetails(Long sessionId) {
        Sessions session = sesrepository.findByIdAndStatus(sessionId, statusValues.ACTIVE);
        if (session == null)
            return null;
        if (session.getEndTime() < System.currentTimeMillis()) {
            session.setStatus(statusValues.INACTIVE);
            sesrepository.save(session);
            return null;
        }
        String username = session.getUsername();
        UserDetails user = repository.findByUsername(username);
        return user;
    }

    @Override
    public UserDetails UpdateContact(Long sessionId, Map<String, Long> detail) {
        Sessions session = sesrepository.findByIdAndStatus(sessionId, statusValues.ACTIVE);
        if (session == null)
            return null;
        if (session.getEndTime() < System.currentTimeMillis()) {
            session.setStatus(statusValues.INACTIVE);
            sesrepository.save(session);
            return null;
        }
        String username = session.getUsername();
        UserDetails user = repository.findByUsername(username);
        user.setMobileNumber(detail.get("contact"));
        repository.save(user);
        return user;
    }

}