package com.tuti.api.authentication;

public class SignInInfo {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public SignInInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public SignInInfo setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SignInInfo setPassword(String password) {
        this.password = password;
        return this;
    }
}
