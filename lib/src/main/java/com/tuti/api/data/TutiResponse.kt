package com.tuti.api.data

import com.google.gson.Gson
import com.tuti.api.data.PaymentToken
import com.tuti.api.ebs.EBSResponse
import kotlinx.serialization.SerialName
import java.io.Reader
import java.io.StringReader
import java.util.*

/**
 * TutiResponse class the encapsulates all noebs responses. Reference class to get
 * values for about any field
 */

@kotlinx.serialization.Serializable
data class TutiResponse(
        val message: String = "",
        val code: String = "",
        val uuid: String = "",
        val result: String = "",
        @SerialName("biller_id")
        val billerId: String = "",
        val eBSFailedResponse: EBSResponse = EBSResponse(),

        val token: String = "",

        val status: String = "",

        @SerialName("due_amount")
        val dueAmount: DueAmount = DueAmount(),
        val fees: Fees? = Fees(),

        @SerialName("ebs_response")
        val ebsResponse: EBSResponse = EBSResponse()
) {

    fun getRawPaymentToken(): String {
        return result;
    }

    // getPaymentTokenUUID in payment token api, we use uuid field to denote
    // the payment token
    fun getPaymentTokenUUID(): String {
        return uuid;
    }

    val encodedQRToken: ByteArray
        get() = Base64.getDecoder().decode(result)

    fun getQRToken(): PaymentToken? {
        val gson = Gson()
        val initialArray = encodedQRToken
        val targetReader: Reader = StringReader(String(initialArray))
        return try {
            val pt = gson.fromJson(targetReader, PaymentToken::class.java)
            targetReader.close()
            pt
        } catch (e: Exception) {
            null
        }
    }

    // get EBS public key for IPIN generation
    fun getiPINKey(): String {
        return this.ebsResponse.pubKeyValue
    }

    /**
     * get EBS public key for encryption (used throughout the code to encrypt all transactions except for IPIN generation. Check {@link #getiPINKey()} instead
     */
    fun getKey(): String {
        return this.ebsResponse.pubKeyValue
    }
}