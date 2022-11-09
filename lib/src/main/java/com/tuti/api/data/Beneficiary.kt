package com.tuti.api.data

import kotlinx.serialization.SerialName


@kotlinx.serialization.Serializable
data class NoebsBeneficiary(
        @SerialName("data") val data: String,
        @SerialName("bill_type") val bill_type: String,
        @SerialName("name") val name: String,
)