package com.tuti.api.data

import okhttp3.Headers.get
import okhttp3.logging.HttpLoggingInterceptor.setLevel
import okhttp3.OkHttpClient.Builder.addInterceptor
import okhttp3.OkHttpClient.Builder.connectTimeout
import okhttp3.OkHttpClient.Builder.readTimeout
import okhttp3.OkHttpClient.Builder.writeTimeout
import okhttp3.OkHttpClient.Builder.build
import okhttp3.Request.Builder.url
import okhttp3.Request.Builder.header
import okhttp3.Request.Builder.post
import okhttp3.Request.Builder.delete
import okhttp3.Request.Builder.put
import okhttp3.Request.Builder.get
import okhttp3.Request.Builder.build
import okhttp3.OkHttpClient.newCall
import okhttp3.Call.execute
import okhttp3.Response.code
import okhttp3.Response.body
import okhttp3.ResponseBody.string
import okhttp3.Response.headers
import com.google.gson.annotations.SerializedName
import com.tuti.api.ebs.PaymentDetails
import java.text.DateFormat
import java.text.SimpleDateFormat
import com.tuti.api.ebs.LastTransactions
import com.tuti.model.PayeeID
import java.text.NumberFormat
import java.text.DecimalFormat
import com.tuti.api.ebs.ErrorDetails
import com.tuti.api.ebs.EBSResponse
import com.tuti.api.data.PaymentToken
import com.google.gson.Gson
import com.tuti.api.data.Fees
import com.tuti.api.authentication.SignInRequest
import com.tuti.api.TutiApiClient.ResponseCallable
import com.tuti.api.authentication.SignInResponse
import com.tuti.api.TutiApiClient.ErrorCallable
import com.tuti.api.data.TutiResponse
import com.tuti.api.data.RequestMethods
import com.tuti.model.Operations
import com.tuti.api.authentication.SignUpRequest
import com.tuti.api.authentication.SignUpResponse
import com.tuti.api.ebs.EBSRequest
import com.tuti.api.data.Cards
import java.lang.Runnable
import okhttp3.OkHttpClient
import com.tuti.api.TutiApiClient
import okhttp3.RequestBody
import com.tuti.api.data.ResponseData
import kotlin.Throws
import java.io.IOException
import kotlin.jvm.Volatile
import okhttp3.logging.HttpLoggingInterceptor
import java.io.Reader
import java.io.StringReader
import java.lang.Exception
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * TutiResponse class the encapsulates all noebs responses. Reference class to get
 * values for about any field
 */
class TutiResponse {
    var message: String? = null
    var code: String? = null
    private val uuid: String? = null

    // getEncodedQRToken returns a base64 encode of a generated
    val encodedQRToken: ByteArray
        get() = Base64.getDecoder().decode(result)

    // getPaymentTokenUUID in payment token api, we use uuid field to denote
    // the payment token
    fun getPaymentTokenUUID(): String? {
        return uuid
    }

    fun getQRToken(): PaymentToken? {
        val gson = Gson()
        val initialArray = encodedQRToken
        val targetReader: Reader = StringReader(String(initialArray))
        try {
            targetReader.close()
        } catch (e: Exception) {
            return null
        }
        return gson.fromJson(targetReader, PaymentToken::class.java)
    }

    private var count = 0
    fun getCount(): Int {
        return count
    }

    fun setCount(count: Int) {
        this.count = count
    }

    fun getToken(): PaymentToken? {
        return token
    }

    fun setToken(token: PaymentToken?) {
        this.token = token
    }

    private var token: PaymentToken? = null

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
    fun getiPINKey(): String? {
        return getEbsResponse().getPubKeyValue()
    }

    /**
     * get EBS public key for encryption (used throughout the code to encrypt all transactions except for IPIN generation. Check [.getiPINKey] instead
     */
    fun getKey(): String? {
        return getEbsResponse().getPubKeyValue()
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    private var status: String? = null
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