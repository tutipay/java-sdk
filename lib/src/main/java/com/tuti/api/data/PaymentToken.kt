package com.tuti.api.data


import com.google.gson.annotations.SerializedName
import com.tuti.api.ebs.EBSResponse
import java.io.Serializable

/**
 * PaymentToken represents a payment order that a user generates such that it can be charged back
 */
class PaymentToken : Serializable {
    var amount = 0

    @SerializedName("cart_id")
    var cartId: String? = null

    @SerializedName("UUID")
    var uuid: String? = null

    @SerializedName("note")
    var paymentNote: String? = null

    @SerializedName("toCard")
    var cardTobePaid: String? = null
    var transaction: EBSResponse? = null
}