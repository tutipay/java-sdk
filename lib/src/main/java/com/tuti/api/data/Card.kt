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


@kotlinx.serialization.Serializable
data class SignUpCard(
        val name: String,
        @SerialName("exp_date")
        val expDate: String,
        @SerialName("pan")
        val pan: String,
        val mobile: String,
        val password: String
) {
        init {
                require(pan.length >= 16)
                require(expDate.length == 4)
                require(mobile.length == 10)
                require(password.length >= 8 )
        }
}