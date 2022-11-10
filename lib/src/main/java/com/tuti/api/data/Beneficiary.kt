package com.tuti.api.data

import kotlinx.serialization.SerialName


@kotlinx.serialization.Serializable
data class NoebsBeneficiary(
        @SerialName("data") val data: String,
        @SerialName("bill_type") val bill_type: String,
        @SerialName("name") val name: String,
)

data class Ipin(
        val pan: String,
        val expDate: String,
        val phone: String,
)

data class IpinCompletion(
        val pan: String,
        val expDate: String,
        val phone: String,
        val otp: String,
        val uuid: String,
        val ipin: String
)