package com.tuti.api.authentication


import com.google.gson.annotations.SerializedName

class SignInResponse {
    @SerializedName("authorization")
    private var authorizationJWT: String? = null
    fun getAuthorizationJWT(): String? {
        return authorizationJWT
    }

    fun setAuthorizationJWT(authorizationJWT: String?) {
        this.authorizationJWT = authorizationJWT
    }

    fun getUser(): User? {
        return user
    }

    fun setUser(user: User?) {
        this.user = user
    }

    private var user: User? = null
}