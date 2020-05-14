package com.example.UserSession.Services;

import java.util.Map;
import java.util.Random;

import com.example.UserSession.Domain.Sessions;
import com.example.UserSession.Domain.UserDetails;
import com.example.UserSession.Repositories.SessionRepository;
import com.example.UserSession.Repositories.UserRepository;

public class SessionServiceImpl implements SessionService {

    private final SessionRepository sesrepository;
    private final UserRepository userrepository;

    SessionServiceImpl(SessionRepository sesrepository, UserRepository userrepository) {
        this.sesrepository = sesrepository;
        this.userrepository = userrepository;
    }

    @Override
    public Sessions login(Map<String, String> details) {
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

    @Override
    public UserDetails logOut(Long sessionId) {
        Sessions session = sesrepository.getOne(sessionId);
        if (session == null)
            return null;
        sesrepository.deleteById(sessionId);
        String username = session.getUsername();
        UserDetails user = userrepository.findByUsername(username);
        return user;
    }

}