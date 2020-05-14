package com.example.UserSession.Domain;

import java.sql.Time;

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

    public Sessions() {
    }

    public Sessions(String username, Long id) {
        this.username = username;
        this.id = id;
    }

}