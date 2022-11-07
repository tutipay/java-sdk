package com.tuti.api.authentication

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class User(
        val id: Int = 0,
        val createdAt: String = "",
        val updateAt: String = "",
        val deletedAt: String = "",
        val username: String = "",
        val fullname: String = "",
        val birthday: String = "",

        @SerialName("mobile")
        val mobileNumber: String = "",
        val email: String = "",

        @SerialName("is_merchant")
        val isMerchant: Boolean = false,
)