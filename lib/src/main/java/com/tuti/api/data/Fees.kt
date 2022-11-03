package com.tuti.api.data

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Fees(
        @SerialName("mohe_fees")
        val moheFees: Float = 0f,

        @SerialName("p2p_fees")
        val p2pFees: Float = 0f,

        @SerialName("custom_fees")
        val customFees: Float = 0f,

        @SerialName("special_payment_fees")
        val specialPaymentFees: Float = 0f,
)