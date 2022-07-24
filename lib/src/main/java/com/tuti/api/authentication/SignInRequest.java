package com.tuti.api.authentication;

import com.google.gson.annotations.SerializedName;

public class SignInRequest {
    private String username;
    private String password;

    public String getOldToken() {
        return oldToken;
    }

    public void setOldToken(String oldToken) {
        this.oldToken = oldToken;
    }

    @SerializedName("authorization")
    private String oldToken;

    public String getUsername() {
        return username;
    }

    public SignInRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public SignInRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SignInRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
