package com.tuti.api.authentication;

import com.google.gson.annotations.SerializedName;

public class SignInRequest {
    private String username;
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private String mobile;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String signature;
    private String message;

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

    /**
     * Use to sign in a user given their mobile number and password.
     * @param mobile
     * @param password
     */
    public SignInRequest(String mobile, String password) {
        this.username = mobile;
        this.password = password;
        this.mobile = mobile;
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
