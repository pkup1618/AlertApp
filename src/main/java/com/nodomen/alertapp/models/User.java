package com.nodomen.alertapp.models;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "user")
public class User {

    @Id
    public Long id;

    private String nickname;
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    private String passwordHash;
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public User() {}

    public User(String nickname, String passwordHash) {
        setNickname(nickname);
        setPasswordHash(passwordHash);
    }
}
