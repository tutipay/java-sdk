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
class TutiResponse(
        val message: String? = null,
        val code: String? = null,
        val uuid: String? = null,
        val result: String? = null,
        @SerialName("biller_id")
        val billerId: String? = null,
        val eBSFailedResponse: EBSResponse? = null,

        val token: String? = null,

        private val status: String? = null,

        @SerialName("due_amount")
        private val dueAmount: DueAmount? = null,
        private val fees: Fees? = null,

        @SerialName("ebs_response")
        private val ebsResponse: EBSResponse? = null,
) {
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
}