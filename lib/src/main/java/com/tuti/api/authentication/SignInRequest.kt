package com.tuti.api.authentication

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class SignInRequest(
        val username: String = "",
        val password: String = "",
        val otp: String = "",

        /**
         * the update new password to be used for password change api
         * @param newPassword
         */
        @SerialName("new_password")
        val newPassword: String = "",
        val mobile: String = "",
        val signature: String = "",
        val message: String = "",

        @SerialName("authorization")
        val oldToken: String = "",

        /**
         * Use to sign in a user given their mobile number and password.
         * @param mobile
         * @param password
         */

)