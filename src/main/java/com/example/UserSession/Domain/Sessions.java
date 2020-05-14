package com.example.UserSession.Domain;

import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "sessions")
public class Sessions {
    @Id
    private Long id;
    private String username;
    private Long startTime;
    private Long endTime;
    private statusValues status;

    public enum statusValues {
        ACTIVE, INACTIVE
    };

    public Sessions() {
    }

    public Sessions(String username, Long id) {
        this.username = username;
        this.id = id;
        // startTime = new Timestamp(System.currentTimeMillis()).toString();
        startTime = System.currentTimeMillis();
        endTime = startTime + 1800000;
        status = statusValues.ACTIVE;
    }

    public Long getStartTime() {
        return startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

}