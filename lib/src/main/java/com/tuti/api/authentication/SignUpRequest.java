package com.tuti.api.authentication;

import com.google.gson.annotations.SerializedName;

public class SignUpRequest {
    private String username;
    private String password;

    public String getFirebaseVerificationID() {
        return firebaseVerificationID;
    }

    public void setFirebaseVerificationID(String firebaseVerificationID) {
        this.firebaseVerificationID = firebaseVerificationID;
    }

    private String firebaseVerificationID;

    public String getUserPubKey() {
        return userPubKey;
    }

    public void setUserPubKey(String userPubKey) {
        this.userPubKey = userPubKey;
    }

    @SerializedName("user_pubkey")
    private String userPubKey;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @SerializedName("device_id")
    private String deviceId;
    private String fullname;
    private String birthday;
    private String email;

    @SerializedName("mobile")
    private String mobileNumber;

    @SerializedName("is_merchant")
    private boolean isMerchant;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public boolean isMerchant() {
        return isMerchant;
    }

    public void setMerchant(boolean merchant) {
        isMerchant = merchant;
    }

}
