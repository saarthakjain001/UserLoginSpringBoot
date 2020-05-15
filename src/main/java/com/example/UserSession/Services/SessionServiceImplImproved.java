package com.example.UserSession.Services;

import java.util.Map;
import java.util.Random;

import com.example.UserSession.Domain.Sessions;
import com.example.UserSession.Domain.UserDetails;
import com.example.UserSession.Domain.Sessions.statusValues;
import com.example.UserSession.Repositories.SessionRepository;
import com.example.UserSession.Services.Exceptions.SessionIdInvalidException;

import org.springframework.stereotype.Service;

@Service
public class SessionServiceImplImproved implements SessionServiceImproved {

    private final SessionRepository sesrepository;

    SessionServiceImplImproved(SessionRepository sesrepository) {
        this.sesrepository = sesrepository;
    }

    @Override
    public Sessions generateSession(Map<String, String> details) {
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
    }

    @Override
    public Sessions endSession(Long sessionId) {
        Sessions session = sesrepository.getOne(sessionId);
        if (session == null)
            throw new SessionIdInvalidException(String.format("The session Id = %s is invalid", sessionId));
        // sesrepository.deleteById(sessionId);
        if (session.getStatus().equals(statusValues.INACTIVE))
            throw new SessionIdInvalidException(String.format("The session Id = %s is invalid", sessionId));
        session.setStatus(statusValues.INACTIVE);
        sesrepository.save(session);
        return session;
    }

    @Override
    public Sessions validateSession(Long sessionId) {
        Sessions session = sesrepository.findByIdAndStatus(sessionId, statusValues.ACTIVE);
        if (session == null)
            throw new SessionIdInvalidException(String.format("The session Id = %s is invalid", sessionId));
        return session;
    }

}