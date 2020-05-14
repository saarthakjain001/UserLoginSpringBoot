package com.example.UserSession.Domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class UserDetailsDTO {
    private String username;
    private String name;
    private Long mobileNumber;

    public UserDetailsDTO() {

    }

}