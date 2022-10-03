package com.tuti.api.data

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.tuti.api.ebs.EBSResponse
import java.io.Reader
import java.io.Serializable
import java.io.StringReader
import java.lang.Exception
import java.util.*

/**
 * TutiResponse class the encapsulates all noebs responses. Reference class to get
 * values for about any field
 */
class TutiResponse {
    var message: String? = null
    var code: String? = null
    val uuid: String? = null

    // getEncodedQRToken returns a base64 encode of a generated
    val encodedQRToken: ByteArray
        get() = Base64.getDecoder().decode(result)

    // getPaymentTokenUUID in payment token api, we use uuid field to denote
    // the payment token
    fun getPaymentTokenUUID(): String? {
        return uuid
    }

    /**
     * getQRToken is a helper method to parse `result` or payment token `token` into a PaymentToken
     * class that can be used to inquire about the payment details.
     */
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

    private var count = 0
    fun getCount(): Int {
        return count
    }

    fun setCount(count: Int) {
        this.count = count
    }

    fun getToken(): String? {
        return token
    }

    private val token: String? = null

    //getRawPaymentToken returns a base64 encoded representation for the payment token.
    // a user can either decode to extract payment info, or use GET /payment_token with
    // the companion uuid to get server's inquiry about the id
    var result: String? = null
        get() = field

    fun getAuthorization(): String? {
        return authorization
    }

    fun setAuthorization(authorization: String?) {
        this.authorization = authorization
    }

    private var authorization: String? = null
    fun getStatus(): String? {
        return status
    }

    // get EBS public key for IPIN generation
    fun getiPINKey(): String {
        return getEbsResponse()!!.pubKeyValue
    }

    /**
     * get EBS public key for encryption (used throughout the code to encrypt all transactions except for IPIN generation. Check [.getiPINKey] instead
     */
    fun getKey(): String {
        return getEbsResponse()!!.pubKeyValue
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    private var status: String? = null
    fun getDueAmount(): DueAmount? {
        return dueAmount
    }

    @SerializedName("due_amount")
    private val dueAmount: DueAmount? = null
    private var fees: Fees? = null

    @SerializedName("ebs_response")
    private var ebsResponse: EBSResponse? = null
    fun getEbsResponse(): EBSResponse? {
        return ebsResponse
    }

    fun setEbsResponse(ebsResponse: EBSResponse?) {
        this.ebsResponse = ebsResponse
    }

    fun getFees(): Fees? {
        return fees
    }

    fun setFees(fees: Fees?) {
        this.fees = fees
    }

}

class DueAmount : Serializable {
    @SerializedName("due_amount")
    var dueAmount: String? = null

    @SerializedName("total_amount")
    var totalAmount: String? = null

    @SerializedName("min_amount")
    var minAmount: String? = null

    @SerializedName("paid_amount")
    var paidAmount: String? = null
}