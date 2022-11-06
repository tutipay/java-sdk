package com.tuti.api.ebs

import java.io.Serializable

@kotlinx.serialization.Serializable
class PaymentDetails : Serializable {
    var account: String? = null
    var amount: Float? = null
    var description: String? = null
}