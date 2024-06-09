package com.imperial.academia.entity;

import java.sql.Timestamp;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;
    private Timestamp registrationDate;

    public User(int id, String username, String password, String email, String role, Timestamp registrationDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.registrationDate = registrationDate;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Timestamp getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(Timestamp registrationDate) { this.registrationDate = registrationDate; }
}
