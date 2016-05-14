package com.example.genceozer.utodo.entities;

/**
 * Created by genceozer on 06/05/16.
 */
public class User {

    private Object userId;
    private String username;
    private String email;

    public User() {
    }

    public User(Object userId, String username, String email) {

        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
