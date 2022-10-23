package com.tuti.api.data

import com.google.common.base.Objects
import com.google.gson.annotations.SerializedName

data class Card(
    val name: String? = null,

    @SerializedName("exp_date")
    val expiryDate: String? = null,

    @SerializedName("pan")
    val PAN: String? = null,

    val mobile: String? = null,

    @SerializedName("card_index")
    val cardIndex: String? = null
)
