package com.tuti.api.authentication


import com.google.gson.annotations.SerializedName


class SignUpRequest {
    private var username: String? = null
    private var password: String? = null
    fun getFirebaseVerificationID(): String? {
        return firebaseVerificationID
    }

    fun setFirebaseVerificationID(firebaseVerificationID: String?) {
        this.firebaseVerificationID = firebaseVerificationID
    }

    @SerializedName("firebase_token")
    private var firebaseVerificationID: String? = null
    fun getUserPubKey(): String? {
        return userPubKey
    }

    fun setUserPubKey(userPubKey: String?) {
        this.userPubKey = userPubKey
    }

    @SerializedName("user_pubkey")
    private var userPubKey: String? = null
    fun getDeviceId(): String? {
        return deviceId
    }

    fun setDeviceId(deviceId: String?) {
        this.deviceId = deviceId
    }

    @SerializedName("device_id")
    private var deviceId: String? = null
    private var fullname: String? = null
    private var birthday: String? = null
    private var email: String? = null

    @SerializedName("mobile")
    private var mobileNumber: String? = null

    @SerializedName("is_merchant")
    private var isMerchant = false
    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String?) {
        this.password = password
    }

    fun getFullname(): String? {
        return fullname
    }

    fun setFullname(fullname: String?) {
        this.fullname = fullname
    }

    fun getBirthday(): String? {
        return birthday
    }

    fun setBirthday(birthday: String?) {
        this.birthday = birthday
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getMobileNumber(): String? {
        return mobileNumber
    }

    fun setMobileNumber(mobileNumber: String?) {
        this.mobileNumber = mobileNumber
    }

    fun isMerchant(): Boolean {
        return isMerchant
    }

    fun setMerchant(merchant: Boolean) {
        isMerchant = merchant
    }
}