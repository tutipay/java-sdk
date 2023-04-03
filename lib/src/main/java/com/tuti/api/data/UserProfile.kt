package com.tuti.api.data

@kotlinx.serialization.Serializable
data class UserProfile(
    val fullname:String,
    val username:String,
    val email:String,
    val birthday:String,
    val gender:String,
)