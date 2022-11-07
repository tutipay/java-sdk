package com.tuti.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class SignInRequest(
        @SerialName("password")
        val password: String? = "",
        @SerialName("otp")
        val otp: String? = "",
        @SerialName("mobile")
        val mobile: String,
        @SerialName("authorization")
        val auth: String? = "",
        @SerialName("signature")
        val signature: String? = "",
        val message: String? = "",
)

@kotlinx.serialization.Serializable
data class SignupRequest(

        @SerialName("password")
        val password: String,
        @SerialName("otp")
        val otp: String? = "",
        @SerialName("mobile")
        val mobile: String,
        @SerialName("authorization")
        val auth: String? = "",
        @SerialName("signature")
        val signature: String? = "",
        val message: String? = "",
        val fullname: String,
        val password2: String? = "",
        @SerialName("user_pubkey")
        val pubkey: String,
)

@kotlinx.serialization.Serializable
data class Beneficiary(
        var billType: Int? = 0,
        var operator: Int? = 0,
        var number: String,
        var carrierPlan: CarrierPlan? = CarrierPlan.PREPAID,
)


@kotlinx.serialization.Serializable
data class NoebsBeneficiary(
        val UserID: Int,
        @SerialName("data") var data: String,
        @SerialName("bill_type") var bill_type: String,
)

data class ben(
        val data: List<NoebsBeneficiary>
)

fun NoebsBeneficiary.toTuti(): Beneficiary {
    var beneficiary = Beneficiary(number = this.data)
    when (this.bill_type) {
        "0010010001" -> {
            beneficiary.billType = 0
            beneficiary.operator = Operator.ZAIN.ordinal
            beneficiary.carrierPlan = CarrierPlan.PREPAID
        }
        "0010010002" -> {
            beneficiary.billType = 0
            beneficiary.operator = Operator.ZAIN.ordinal
            beneficiary.carrierPlan = CarrierPlan.POSTPAID
        }
        "0010010005" -> { // sudani
            beneficiary.billType = 0
            beneficiary.operator = Operator.SUDANI.ordinal
            beneficiary.carrierPlan = CarrierPlan.POSTPAID
        }
        "0010010006" -> { // sudani
            beneficiary.billType = 0
            beneficiary.operator = Operator.SUDANI.ordinal
            beneficiary.carrierPlan = CarrierPlan.POSTPAID
        }
        "0010010003" -> { // mtn
            beneficiary.billType = 0
            beneficiary.operator = Operator.MTN.ordinal
            beneficiary.carrierPlan = CarrierPlan.POSTPAID
        }
        "0010010004" -> { // mtn postpaid
            beneficiary.billType = 0
            beneficiary.operator = Operator.MTN.ordinal
            beneficiary.carrierPlan = CarrierPlan.POSTPAID
        }
        "0010020001" -> beneficiary.billType = 1
        "p2p" -> beneficiary.billType = 2
        "0010050001" -> beneficiary.billType = 3
        "0010060002" -> beneficiary.billType = 4
        "0010030002" -> beneficiary.billType = 5
        "0010030003" -> beneficiary.billType = 6
        "voucher" -> beneficiary.billType = 7
    }
    return beneficiary
}

fun Beneficiary.toNoebs(): NoebsBeneficiary {
    var noebs = NoebsBeneficiary(0,"", "")
    noebs.data = this.number
    when (this.billType) {
        0 -> {
            if (this.operator == 0) { // zain
                if (this.carrierPlan == CarrierPlan.PREPAID) {
                    noebs.bill_type = "0010010001"
                } else {
                    noebs.bill_type = "0010010002"
                }
            } else if (this.operator == 1) { // sudani
                if (this.carrierPlan == CarrierPlan.PREPAID) {
                    noebs.bill_type = "0010010005"
                } else {
                    noebs.bill_type = "0010010006"
                }
            } else if (this.operator == 2) {
                if (this.carrierPlan == CarrierPlan.PREPAID) {
                    noebs.bill_type = "0010010003"
                } else {
                    noebs.bill_type = "0010010004"
                }
            }
        }
        1 -> noebs.bill_type = "0010020001" // nec
        2 -> noebs.bill_type = "p2p" // p2p
        3 -> noebs.bill_type = "0010050001" // e15
        4 -> noebs.bill_type = "0010060002" // bashair
        5 -> noebs.bill_type = "0010030002" // mohe
        6 -> noebs.bill_type = "0010030003" // customs
        7 -> noebs.bill_type = "voucher" // voucher
    }
    return noebs
}