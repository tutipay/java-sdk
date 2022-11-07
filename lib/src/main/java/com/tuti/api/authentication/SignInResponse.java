package com.tuti.api.authentication;

import com.google.gson.annotations.SerializedName;

import kotlinx.serialization.Serializable;

@Serializable
public class SignInResponse {
    @SerializedName("authorization")
    private String authorizationJWT;

    public String getPubkey() {
        return pubkey;
    }

    public void setPubkey(String pubkey) {
        this.pubkey = pubkey;
    }

    private String pubkey;

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
