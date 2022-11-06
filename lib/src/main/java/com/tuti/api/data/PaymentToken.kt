package com.tuti.api.data

import com.google.gson.Gson
import com.tuti.api.data.PaymentToken
import com.tuti.api.ebs.EBSResponse
import kotlinx.serialization.SerialName
import java.io.Reader
import java.io.StringReader
import java.util.*

/**
 * PaymentToken represents a payment order that a user generates such that it can be charged back
 */
@kotlinx.serialization.Serializable
class PaymentToken(
        val amount: Int = 0,

        @SerialName("cart_id")
        val cartId: String = "",

        @SerialName("UUID")
        val uuid: String = "",

        @SerialName("note")
        val paymentNote: String = "",

        @SerialName("toCard")
        val cardTobePaid: String = "",

        /**
         * Use getTransaction to get the inner transaction response we get from EBS
         * @return
         */
        val transaction: EBSResponse = EBSResponse(),

        @SerialName("is_paid")
        val isPaymentSuccessful: Boolean = false,

        ) {

    companion object {
        /**
         * ParseQRToken creates a PaymentToken object from a base64 encoded QR code. This is method
         * can be used when showing a tuti encoded QR token to a user, the user can then scan that
         * and creates a [PaymentToken] object from the QR.
         * @param b64Token
         * @return
         */
        @JvmStatic
        fun ParseQRToken(b64Token: String?): PaymentToken? {
            val parsedToken = Base64.getDecoder().decode(b64Token)
            val gson = Gson()
            val targetReader: Reader = StringReader(String(parsedToken))
            return try {
                val pt = gson.fromJson(targetReader, PaymentToken::class.java)
                targetReader.close()
                pt
            } catch (e: Exception) {
                null
            }
        }
    }
}