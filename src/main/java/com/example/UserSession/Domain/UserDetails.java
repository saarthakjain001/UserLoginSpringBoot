package com.example.UserSession.Domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class UserDetails {

    private @Id @GeneratedValue Long id;
    private String name;
    private @Column(unique = true) String username;
    private String password;
    private Long mobileNumber;

    UserDetails() {
    }

    UserDetails(String name, String username, String password, Long mobileNumber) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.mobileNumber = mobileNumber;
    }
}