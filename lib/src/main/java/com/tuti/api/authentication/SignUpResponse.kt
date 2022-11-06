package com.tuti.api.authentication

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class SignUpResponse (
    @SerialName("details")
    var user: User = User()
)