package com.example.UserSession.Services;

import java.util.Map;
import java.util.Random;

import com.example.UserSession.Domain.Sessions;
import com.example.UserSession.Domain.UserDetails;
import com.example.UserSession.Domain.Sessions.statusValues;
import com.example.UserSession.Repositories.SessionRepository;
import com.example.UserSession.Repositories.UserRepository;
import com.example.UserSession.Services.Exceptions.InvalidUsernamePasswordException;
import com.example.UserSession.Services.Exceptions.SessionIdInvalidException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
            throw new InvalidUsernamePasswordException("Wrong username or password entered");
        } else {
            if (sesrepository.findByUsernameAndStatus(details.get("username"), statusValues.ACTIVE) == null) {
                Random rand = new Random();

                Sessions newSession = new Sessions(details.get("username"), rand.nextLong());
                sesrepository.save(newSession);
                return newSession;
            } else {
                Sessions temp = sesrepository.findByUsernameAndStatus(details.get("username"), statusValues.ACTIVE);
                temp.setStatus(statusValues.INACTIVE);
                sesrepository.save(temp);
                Random rand = new Random();

                Sessions newSession = new Sessions(details.get("username"), rand.nextLong());
                sesrepository.save(newSession);
                return newSession;
            }
            // return
            // sesrepository.findByUsernameAndStatus(details.get("username"),statusValues.ACTIVE);
        }
    }

    @Override
    public UserDetails logOut(Long sessionId) {
        Sessions session = sesrepository.getOne(sessionId);
        if (session == null)
            throw new SessionIdInvalidException(String.format("The session Id = %s is invalid", sessionId));
        // sesrepository.deleteById(sessionId);
        if (session.getStatus().equals(statusValues.INACTIVE))
            throw new SessionIdInvalidException(String.format("The session Id = %s is invalid", sessionId));
        session.setStatus(statusValues.INACTIVE);
        String username = session.getUsername();
        sesrepository.save(session);
        UserDetails user = userrepository.findByUsername(username);
        return user;
    }

}