package com.tuti.api.data

import com.google.common.base.Objects
import com.google.gson.annotations.SerializedName

data class Card(
    val name: String = "",

    @SerializedName("exp_date")
    val expiryDate: String = "",

    @SerializedName("pan")
    val PAN: String = "",

    val mobile: String = "",

    @SerializedName("card_index")
    val cardIndex: String = ""
)
