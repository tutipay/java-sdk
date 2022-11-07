package com.tuti.api.data

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Card(
        val name: String = "",

        @SerialName("exp_date")
        val expiryDate: String = "",

        @SerialName("pan")
        val PAN: String = "",

        val mobile: String = "",

        @SerialName("card_index")
        val cardIndex: String = ""
)
