package com.tuti.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class SignInRequest(
        @SerialName("password")
        val password: String? = "",
        @SerialName("otp")
        val otp: String? = "",
        @SerialName("mobile")
        val mobile: String,
        @SerialName("authorization")
        val auth: String? = "",
        @SerialName("signature")
        val signature: String? = "",
        val message: String? = "",
)

@kotlinx.serialization.Serializable
data class SignupRequest(

        @SerialName("password")
        val password: String,
        @SerialName("otp")
        val otp: String? = "",
        @SerialName("mobile")
        val mobile: String,
        @SerialName("authorization")
        val auth: String? = "",
        @SerialName("signature")
        val signature: String? = "",
        val message: String? = "",
        val fullname: String,
        val password2: String? = "",
        @SerialName("user_pubkey")
        val pubkey: String,
)

@kotlinx.serialization.Serializable
data class NoebsBeneficiary(
        @SerialName("data") var data: String,
        @SerialName("bill_type") var bill_type: String,
)