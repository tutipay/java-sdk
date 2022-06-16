package com.tuti.api.authentication;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class AuthenticationResponse{
    @SerializedName("authorization")
    private String authorizationJWT;

    public String getAuthorizationJWT() {
        return authorizationJWT;
    }

    public void setAuthorizationJWT(String authorizationJWT) {
        this.authorizationJWT = authorizationJWT;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

}