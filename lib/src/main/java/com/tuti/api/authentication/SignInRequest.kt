package com.tuti.api.authentication

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
class SignInRequest(
        var username: String? = null,
        var password: String? = null,
        var otp: String? = null,

        /**
         * the update new password to be used for password change api
         * @param newPassword
         */
        @SerialName("new_password")
        var newPassword: String? = null,
        var mobile: String? = null,
        var signature: String? = null,
        var message: String? = null,

        @SerialName("authorization")
        var oldToken: String? = null,

        /**
         * Use to sign in a user given their mobile number and password.
         * @param mobile
         * @param password
         */

)