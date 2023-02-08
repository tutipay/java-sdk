package com.tuti.api.data

@kotlinx.serialization.Serializable
data class IsUser (
    val phone: String,
    val is_user: Boolean,
)

data class IsUserResponse (
    val result: List<IsUser>
)