package com.tuti.api.data

@kotlinx.serialization.Serializable
data class Contact(
        val name:String,
        val mobile:String,
)