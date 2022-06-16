package com.tuti.api.authentication;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class UserCredentials {
    @SerializedName("mobile")
    private String mobileNumber;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public UserCredentials setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserCredentials setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public UserCredentials setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }
}
